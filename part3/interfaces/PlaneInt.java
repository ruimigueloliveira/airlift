package interfaces;

import java.rmi.*;

/**
 * Plane interface to implement the stub for other entities.
 * @author Pedro Goncalves
 * @author Rui Oliveira
 */

public interface PlaneInt extends Remote {

	/**
	 *  Operation board the plane
	 *
	 *  It is called by the passenger after showing documents
	 *  
	 *  @param passengerId id of the passenger
	 *  @throws java.rmi.RemoteException if either the invocation of the remote method, or the communication with the registry
	 *  service fails
	 */

	public void boardThePlane(int passengerId) throws RemoteException;

	/**
	 *  Operation fly to destination point
	 *
	 *  It is called by the pilot after waiting for boarding
	 *  
	 *  @throws java.rmi.RemoteException if either the invocation of the remote method, or the communication with the registry
	 *  service fails
	 */
	
	public void flyToDestinationPoint() throws RemoteException;

	/**
	 *  Operation wait for end of flight
	 *
	 *  It is called by the passenger after entering the plane to wait for the end of the flight.
	 *  
	 *  @throws java.rmi.RemoteException if either the invocation of the remote method, or the communication with the registry
	 *  service fails
	 */

	public void waitForEndOfFlight() throws RemoteException;

	/**
	 *  Operation announce arrival
	 *
	 *  It is called by the pilot after waiting for boarding.
	 *  
	 *  @throws java.rmi.RemoteException if either the invocation of the remote method, or the communication with the registry
	 *  service fails
	 */

	public void announceArrival() throws RemoteException;

	/**
	 *  Operation leave the plane
	 *
	 *  It is called by the passenger after being in flight
	 *  
	 *  @throws java.rmi.RemoteException if either the invocation of the remote method, or the communication with the registry
	 *  service fails
	 */

	public void leaveThePlane() throws RemoteException;


	/**
	 * Terminate the Plane service.
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
