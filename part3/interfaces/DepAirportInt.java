package interfaces;

import java.rmi.*;

/**
 * Departure Airport interface to implement the stub for other entities.
 * @author Pedro Goncalves
 * @author Rui Oliveira
 */

public interface DepAirportInt extends Remote {

	/**
	 *  Operation park at transfer gate.
	 *
	 *  It is called by the pilot when the plane is parked at the transfer gate.
	 *  @return true if it was the last flight.
	 *  @throws java.rmi.RemoteException if either the invocation of the remote method, or the communication with the registry
	 *  service fails
	 */

	public boolean parkAtTransferGate() throws RemoteException;

	/**
	 *  Operation inform plane ready for boarding.
	 *
	 *  It is called by the pilot when the plane is ready for boarding.
	 *  @throws java.rmi.RemoteException if either the invocation of the remote method, or the communication with the registry
	 *  service fails
	 */

	public void informPlaneReadyForBoarding() throws RemoteException;

	/**
	 *  Operation wait for all in board.
	 *
	 *  It is called by the pilot when he is waiting for the passengers to board the plane.
	 *  @throws java.rmi.RemoteException if either the invocation of the remote method, or the communication with the registry
	 *  service fails
	 */

	public void waitForAllInBoard() throws RemoteException;

	/**
	 *  Operation prepare for passing boarding.
	 *
	 *  It is called by the hostess to start the boarding process.
	 *  @throws java.rmi.RemoteException if either the invocation of the remote method, or the communication with the registry
	 *  service fails
	 */

	public void prepareForPassBoarding() throws RemoteException;

	/**
	 *  Operation wait in queue.
	 *
	 *  It is called by each passenger when they go to wait in the queue.
	 *  
	 *  @param passengerId id of the passenger
	 *  @throws java.rmi.RemoteException if either the invocation of the remote method, or the communication with the registry
	 *  service fails
	 */

	public void waitInQueue(int passengerId) throws RemoteException;

	/**
	 *  Operation wait for next passenger.
	 *
	 *  It is called by the hostess when she's ready to check the next passenger.
	 *
	 *  @return passenger id or -1 if the plane is ready to take off.
	 *  @throws java.rmi.RemoteException if either the invocation of the remote method, or the communication with the registry
	 *  service fails
	 */

	public int waitForNextPassenger() throws RemoteException;

	/**
	 *  Operation show documents.
	 *
	 *  It is called by the passengers when they show the documents to the hostess.
	 *  
	 *  @param passengerId id of the passenger
	 *  @throws java.rmi.RemoteException if either the invocation of the remote method, or the communication with the registry
	 *  service fails
	 */

	public void showDocuments(int passengerId) throws RemoteException;

	/**
	 *  Operation check documents.
	 *
	 *  It is called by the hostess when she checks the passenger's documents,
	 *  and also makes sure it's the correct passenger.
	 *
	 * @param passengerId id of the passenger
	 * @throws java.rmi.RemoteException if either the invocation of the remote method, or the communication with the registry
	 * service fails
	 */

	public void checkDocuments(int passengerId) throws RemoteException;

	/**
	 *  Operation inform plane ready to take off.
	 *
	 *  It is called by the hostess when the plane has a valid number of passengers
	 *  and is ready to take off.
	 *  @throws java.rmi.RemoteException if either the invocation of the remote method, or the communication with the registry
	 *  service fails
	 */

	public void informPlaneReadyToTakeOff() throws RemoteException;

	/**
	 *  Operation wait for next flight.
	 *
	 *  It is called by the hostess when the plane has taken off and
	 *  she is now waiting for the next flight.
	 *  @throws java.rmi.RemoteException if either the invocation of the remote method, or the communication with the registry
	 *  service fails
	 */

	public void waitForNextFlight() throws RemoteException;

	/**
	 *  Operation all passengers left.
	 *
	 *  It is called by the hostess and the pilot to check if there are still passengers to board.
	 *
	 *  @return true if all passengers have left the departing airport.
	 *  @throws java.rmi.RemoteException if either the invocation of the remote method, or the communication with the registry
	 *  service fails
	 */

	public boolean allPassengLeft() throws RemoteException;

	/**
	 * Terminate the Departure airport service.
	 * @throws java.rmi.RemoteException if either the invocation of the remote method, or the communication with the registry
	 *  service fails
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
