package interfaces;

import java.rmi.*;

/**
 * Destination interface to implement the stub for other entities.
 * @author Pedro Goncalves
 * @author Rui Oliveira
 */

public interface DestAirportInt extends Remote {

	/**
	 *  Operation fly to departure point
	 *
	 *  It is called by the pilot after deboarding.
	 *  
	 *  @throws java.rmi.RemoteException if either the invocation of the remote method, or the communication with the registry
	 *  service fails
	 */

	public void flyToDeparturePoint() throws RemoteException;


	/**
	 * Terminate the Destination airport service.
	 * @throws java.rmi.RemoteException if either the invocation of the remote method, or the communication with the registry
	 * service fails
	 */

	public void serviceEnd() throws RemoteException;

	/**
	 *   Operation server shutdown.
	 *
	 *   New operation.
	 *
	 *   @throws RemoteException if either the invocation of the remote method, or the communication with the registry
	 *   service fails
	 */

	public void shutdown () throws RemoteException;
}
