package serverSide.objects;

import java.rmi.*;
import interfaces.*;
import serverSide.main.*;
import clientSide.entities.*;
import commInfra.*;
import genclass.GenericIO;

/**
 * Definition of the Departure Airport
 * Communication is based on Java RMI.
 *
 */

public class DepartureAirport implements DepAirportInt {

	/**
	 *  Number of passengers in queue.
	 */

	private int nQueue;


	/**
	 *   Reference to the general repository stub.
	 */

	private final GeneralReposInt repos;

	/**
	 *   Passengers in queue.
	 */

	private MemFIFO<Integer> inQueue;

	/**
	 *   Array that checks if each passenger has shown their documents.
	 */

	private boolean [] documentsShowed = new boolean[SimulPar.K];

	/**
	 *   Array that checks each passengers state.
	 */

	private int [] stateID = new int[SimulPar.K];

	/**
	 *   Total passengers that are on the plane.
	 */

	private int passengersOnPlane = 0;

	/**
	 *   Number of passengers that still haven't departured.
	 */

	private int remainingPassengers = SimulPar.K;

	/**
	 *   Id of the passenger that is being checked for his documents.
	 */

	private int actualId = -1;

	/**
	 *   Number of the current flight.
	 */

	private int flightNum = 0;

	/**
	 *   true if the plane is ready to take off.
	 */

	private boolean readyToTakeOff = false;

	/**
	 *   true if the plane is ready for boarding.
	 */

	private boolean readyForBoarding = false;

	/**
	 *   true if it's the last flight.
	 */

	private boolean lastFlight = false;

	/**
	 *   true if hostess can inform the pilot that the plane is ready to take off.
	 */

	private boolean informPilot = false;

	/**
	 *  Departure airport instantiation.
	 *
	 *    @param repos reference to the general repository
	 */

	public DepartureAirport(GeneralReposInt repos)
	{
		nQueue = 0;
		try
		{
			inQueue = new MemFIFO<> (new Integer [SimulPar.K]);
		} catch (MemException e) {
			GenericIO.writelnString ("Instantiation of waiting FIFO failed: " + e.getMessage ());
			inQueue = null;
			System.exit (1);
		}
		this.repos = repos;
	}

	/**
	 *  Operation park at transfer gate.
	 *
	 *  It is called by the pilot when the plane is parked at the transfer gate.
	 *  @return true if it was the last flight.
	 *  @throws RemoteException if either the invocation of the remote method, or the communication with the registry
	 *  service fails
	 */

	@Override
	public synchronized boolean parkAtTransferGate() throws RemoteException {

		GenericIO.writelnString("Park at transfer gate");
		if (flightNum != 0) {
			repos.setPilotState(PilotStates.ATTRANSFERGATE);
		}
		if (lastFlight || remainingPassengers == 0) {
			readyForBoarding = true;
			notifyAll();
			repos.printFinal();
			return true;
		}
		return false;
	}

	/**
	 *  Operation inform plane ready for boarding.
	 *
	 *  It is called by the pilot when the plane is ready for boarding.
	 *  @throws RemoteException if either the invocation of the remote method, or the communication with the registry
	 *  service fails
	 */

	@Override
	public synchronized void informPlaneReadyForBoarding() throws RemoteException {

		GenericIO.writelnString("Plane ready for flight!");
		readyForBoarding = true;
		flightNum++;
		if (remainingPassengers <= SimulPar.MIN) {
			lastFlight = true;
		}
		repos.print("\nFlight " + flightNum + ": boarding started.");
		repos.setPilotState(PilotStates.READYFORBOARDING);
	}

	/**
	 *  Operation wait for all in board.
	 *
	 *  It is called by the pilot when he is waiting for the passengers to board the plane.
	 *  @throws RemoteException if either the invocation of the remote method, or the communication with the registry
	 *  service fails
	 */

	@Override
	public synchronized void waitForAllInBoard() throws RemoteException {
		repos.setPilotState(PilotStates.WAITINGFORBOARDING);

		notifyAll();

		while (!informPilot)
		{
			try
			{
				wait ();
			}
			catch (InterruptedException e) {}
		}
		informPilot = false;
	}

	/**
	 *  Operation prepare for passing boarding.
	 *
	 *  It is called by the hostess to start the boarding process.
	 *  @throws RemoteException if either the invocation of the remote method, or the communication with the registry
	 *  service fails
	 */

	@Override
	public synchronized void prepareForPassBoarding() throws RemoteException {

		// Hostess is waiting for the pilot info
		while (!readyForBoarding)
		{
			try
			{
				GenericIO.writelnString("Waiting for boarding!");
				wait ();
			}
			catch (InterruptedException e) {}
		}
		GenericIO.writelnString("Preparing for boarding!");
	}

	/**
	 *  Operation wait in queue.
	 *
	 *  It is called by each passenger when they go to wait in the queue.
	 *  
	 *  @param passengerId id of the passenger
	 *  @throws RemoteException if either the invocation of the remote method, or the communication with the registry
	 *  service fails
	 */

	@Override
	public synchronized void waitInQueue(int passengerId) throws RemoteException
	{
		try
		{
			GenericIO.writelnString("Passenger " + passengerId + " added to queue");
			inQueue.write(passengerId);                    	// the passenger enters the Queue
			nQueue++;
			repos.addToQ();									// update the repository
			documentsShowed[passengerId] = false;
		}
		catch (MemException e)
		{
			GenericIO.writelnString ("Insertion of passenger id in queue failed: " + e.getMessage ());
			System.exit (1);
		}

		repos.setPassengerState (passengerId, PassengerStates.INQUEUE);

		notifyAll ();


		while (actualId != passengerId)
		{
			try
			{
				wait ();
			}
			catch (InterruptedException e) {}
		}
	}

	/**
	 *  Operation wait for next passenger.
	 *
	 *  It is called by the hostess when she's ready to check the next passenger.
	 *
	 *  @return passenger id or -1 if the plane is ready to take off.
	 *  @throws RemoteException if either the invocation of the remote method, or the communication with the registry
	 *  service fails
	 */

	@Override
	public synchronized int waitForNextPassenger() throws RemoteException {

		readyForBoarding = false;
		repos.setHostessState(HostessStates.WAITFORPASSENGER);

		if (passengersOnPlane == SimulPar.MAX) {
			readyToTakeOff = true;
			return -1;
		}

		if (remainingPassengers == 0 && lastFlight) {
			readyToTakeOff = true;
			return -1;
		}

		while(nQueue == 0) {
			try {
				if (passengersOnPlane >= SimulPar.MIN) {

					readyToTakeOff = true;
					return -1;
				}
				wait();
			} catch(Exception e) {
				return -1;
			}
		}
		nQueue -= 1;
		repos.removeFromQ();
		int passengerId = -1;
		try {
			passengerId = inQueue.read();
			actualId = passengerId;

		} catch (MemException e) {
			e.printStackTrace();
		}

		notifyAll();

		GenericIO.writelnString("Waiting for passenger " + passengerId);

		return passengerId;
	}

	/**
	 *  Operation show documents.
	 *
	 *  It is called by the passengers when they show the documents to the hostess.
	 *  
	 *  @param passengerId id of the passenger
	 *  @throws RemoteException if either the invocation of the remote method, or the communication with the registry
	 *  service fails
	 */

	@Override
	public synchronized void showDocuments(int passengerId) throws RemoteException {

		System.out.println("Passenger " + passengerId + " Showting docd");
		documentsShowed[passengerId] = true;
		notifyAll();
		while (stateID[passengerId] != PassengerStates.INFLIGHT) {
			try {
				wait();
			} catch(InterruptedException e) {}
		}
	}

	/**
	 *  Operation check documents.
	 *
	 *  It is called by the hostess when she checks the passenger's documents,
	 *  and also makes sure it's the correct passenger.
	 *
	 * @param passengerID id of the passenger
	 * @throws RemoteException if either the invocation of the remote method, or the communication with the registry
	 * service fails
	 */

	@Override
	public synchronized void checkDocuments(int passengerID) throws RemoteException{

		while (!documentsShowed[passengerID] || actualId != passengerID)
		{
			try
			{
				wait ();
			}
			catch (InterruptedException e) {}
		}

		notifyAll();

		repos.print("\nFlight " + flightNum + ": passenger " + passengerID + " checked.");

		repos.setHostessState(HostessStates.CHECKPASSENGER);
		remainingPassengers--;
		GenericIO.writelnString("Remaining passengers: " + remainingPassengers);
		GenericIO.writelnString("Checking passenger " + passengerID + " documents!");

		stateID[passengerID] = PassengerStates.INFLIGHT;

		repos.addToF();
		repos.setPassengerState (passengerID, PassengerStates.INFLIGHT);

		passengersOnPlane++;
		if (remainingPassengers == 0 && lastFlight) {
			readyToTakeOff = true;
			readyForBoarding = true;
		}
	}

	/**
	 *  Operation inform plane ready to take off.
	 *
	 *  It is called by the hostess when the plane has a valid number of passengers
	 *  and is ready to take off.
	 *  @throws RemoteException if either the invocation of the remote method, or the communication with the registry
	 *  service fails
	 */

	@Override
	public synchronized void informPlaneReadyToTakeOff() throws RemoteException {

		while (!readyToTakeOff)
		{
			try
			{
				wait ();
			}
			catch (InterruptedException e) {}
		}
		readyToTakeOff = false;
		GenericIO.writelnString("Plane ready to take off with " + passengersOnPlane + " passengers!!");

		repos.print("\nFlight " + flightNum + ": departed with " + passengersOnPlane + " passengers.");

		notifyAll();
		repos.setHostessState(HostessStates.READYTOFLY);

		informPilot = true;
	}

	/**
	 *  Operation wait for next flight.
	 *
	 *  It is called by the hostess when the plane has taken off and
	 *  she is now waiting for the next flight.
	 *  @throws RemoteException if either the invocation of the remote method, or the communication with the registry
	 *  service fails
	 */

	@Override
	public synchronized void waitForNextFlight() throws RemoteException {

		System.out.println("Hostess entra no waitForNextFlight: " + readyForBoarding);
		passengersOnPlane = 0;
		repos.setHostessState(HostessStates.WAITFORFLIGHT);

		while(!readyForBoarding) {
			try {
				wait();
			} catch(Exception e) {}
		}
		notifyAll();
		System.out.println("Sai do wait");
	}

	/**
	 *  Operation all passengers left.
	 *
	 *  It is called by the hostess and the pilot to check if there are still passengers to board.
	 *
	 *  @return true if all passengers have left the departing airport.
	 *  @throws RemoteException if either the invocation of the remote method, or the communication with the registry
	 *  service fails
	 */

	@Override
	public boolean allPassengLeft() throws RemoteException {

		if (remainingPassengers == 0) {
			readyForBoarding = true;
			return true;
		}
		return false;
	}

	/**
	 * Terminate the Departure airport service.
	 * @throws RemoteException if either the invocation of the remote method, or the communication with the registry
	 * service fails
	 */

	@Override
	public void serviceEnd() throws RemoteException {
		MainDepartureAirport.serviceEnd = true;
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
		MainDepartureAirport.shutdown ();
		notifyAll();
	}
}
