package serverSide.objects;

import java.rmi.*;
import interfaces.*;
import serverSide.main.*;
import clientSide.entities.*;
import commInfra.*;
import genclass.GenericIO;

/**
 * Definition of the Plane
 *
 */

public class Plane implements PlaneInt {

	/**
	 *   Reference to the general repository.
	 */

	private final GeneralReposInt repos;

	/**
	 *   Passengers on the plane
	 */

	private MemFIFO<Integer> onPlane;

	/**
	 *   True if the plane reached its destination
	 */
	
	private boolean atDestination = false;


	/**
	 * True if all passengers have left
	 */

	private boolean allPassengersLeave = false;

	/**
	 * Flight number
	 */

	private int flightNum = 0;

	/**
	 * Numbers of passenger in flight
	 */

	private int flightPasseng = 0;

	/**
	 * Plane instantiation.
	 * @param repos general repository
	 */

	public Plane(GeneralReposInt repos) {
		try
		{
			onPlane = new MemFIFO<> (new Integer [SimulPar.K]);
		} catch (MemException e)
		{
			GenericIO.writelnString ("Instantiation of waiting FIFO failed: " + e.getMessage ());
			System.exit (1);
		}
		this.repos = repos;
	}

	/**
	 *  Operation board the plane
	 *
	 *  It is called by the passenger after showing documents
	 *  
	 *  @param passengerId id of the passenger
	 *  @throws RemoteException if either the invocation of the remote method, or the communication with the registry
	 *  service fails
	 */
	
	@Override
	public synchronized void boardThePlane(int passengerId) throws RemoteException {
		allPassengersLeave = false;
		try {
			onPlane.write(passengerId);
			flightPasseng++;
		} catch(Exception e) {
			GenericIO.writelnString ("Error in FIFO: " + e.getMessage ());
		}
		GenericIO.writelnString("Passenger " + passengerId + " is now on the plane");
		notifyAll();
	}

	/**
	 *  Operation fly to destination point
	 *
	 *  It is called by the pilot after waiting for boarding
	 *  
	 *  @throws RemoteException if either the invocation of the remote method, or the communication with the registry
	 *  service fails
	 */
	
	@Override
	public synchronized void flyToDestinationPoint() throws RemoteException {
		GenericIO.writelnString("Plane took off");
		repos.setPilotState(PilotStates.FLYINGFORWARD);
		repos.updateFlightInfo(flightPasseng);
	}

	/**
	 *  Operation wait for end of flight
	 *
	 *  It is called by the passenger after entering the plane to wait for the end of the flight.
	 *  
	 *  @throws RemoteException if either the invocation of the remote method, or the communication with the registry
	 *  service fails
	 */
	
	@Override
	public synchronized void waitForEndOfFlight() throws RemoteException {
		while (!atDestination) {
			try {
				wait();
			} catch(InterruptedException e) {}
		}
	}

	/**
	 *  Operation announce arrival
	 *
	 *  It is called by the pilot after waiting for boarding.
	 *  
	 *  @throws RemoteException if either the invocation of the remote method, or the communication with the registry
	 *  service fails
	 */
	
	@Override
	public synchronized void announceArrival() throws RemoteException {
		GenericIO.writelnString("Plane reached its destination");
		flightNum++;
		repos.print("\nFlight " + flightNum + ": arrived.");
		repos.setPilotState(PilotStates.DEBOARDING);
		atDestination = true;
		notifyAll();
		while(!allPassengersLeave) {
			try {
				wait();
			} catch(Exception e) {}
		}
		GenericIO.writelnString("All passengers left!");
	}


	/**
	 *  Operation leave the plane
	 *
	 *  It is called by the passenger after being in flight
	 *  
	 *  @throws RemoteException if either the invocation of the remote method, or the communication with the registry
	 *  service fails
	 */
	
	@Override
	public synchronized void leaveThePlane() throws RemoteException {
		int passengerId = -1;
		try {
			passengerId = onPlane.read();

			repos.removeFromF();
			repos.addToTotal();
			if (onPlane.isEmpty()) {
				allPassengersLeave = true;
				flightPasseng = 0;
				atDestination = false;
			}
		} catch(Exception e) {
			GenericIO.writelnString ("Error in FIFO: " + e.getMessage ());
		}
		GenericIO.writelnString("Passenger " + passengerId + " is deboarding");
		repos.setPassengerState (passengerId, PassengerStates.ATDESTINATION);
		notifyAll();
	}

	/**
	 * Terminate the Plane service.
	 * @throws RemoteException if either the invocation of the remote method, or the communication with the registry
	 * service fails
	 */
	
	@Override
	public void serviceEnd() throws RemoteException {
		MainPlane.serviceEnd = true;
	}

	/**
	 *   Operation server shutdown.
	 *
	 *   New operation.
	 *
	 *   @throws RemoteException if either the invocation of the remote method, or the communication with the registry
	 *   service fails
	 */

	@Override
	public synchronized void shutdown () throws RemoteException
	{
		if(allPassengersLeave)
			MainPlane.shutdown ();
		notifyAll();
	}
}
