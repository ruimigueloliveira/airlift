package serverSide.objects;

import java.rmi.*;
import interfaces.*;
import serverSide.main.*;
import clientSide.entities.*;
import genclass.GenericIO;

/**
 * Definition of the Destination Airport
 *
 */

public class DestinationAirport implements DestAirportInt {

	/**
	 *   Reference to the general repository.
	 */

	private final GeneralReposInt repos;

	/**
	 *  Flight number
	 */

	private int flightNum = 0;

	/**
	 *   Instantiation of the destination airport
	 *
	 *   @param repos general repository
	 */

	public DestinationAirport(GeneralReposInt repos)
	{
		this.repos = repos;
	}

	/**
	 *  Operation fly to departure point
	 *
	 *  It is called by the pilot after deboarding.
	 *  
	 *  @throws RemoteException if either the invocation of the remote method, or the communication with the registry
	 *  service fails
	 */

	@Override
	public synchronized void flyToDeparturePoint() throws RemoteException
	{
		GenericIO.writelnString("Plane took off");
		flightNum++;
		repos.print("\nFlight " + flightNum + ": returning");
		repos.setPilotState(PilotStates.FLYINGBACK);
		GenericIO.writelnString("End of flight " + flightNum + "!");
	}

	/**
	 * Terminate the Destination airport service.
	 * @throws RemoteException if either the invocation of the remote method, or the communication with the registry
	 * service fails
	 */

	@Override
	public void serviceEnd() throws RemoteException {
		MainDestinationAirport.serviceEnd = true;
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
		MainDestinationAirport.shutdown ();
		notifyAll();
	}
}
