package interfaces;

import java.rmi.*;

/**
 * General repository interface to implement the stub for other entities.
 * @author Pedro Goncalves
 * @author Rui Oliveira
 */

public interface GeneralReposInt extends Remote {

	/**
	 *   Set passenger state.
	 *
	 *     @param id passenger id
	 *     @param state passenger state
	 *     @throws java.rmi.RemoteException if either the invocation of the remote method, or the communication with the registry
	 *     service fails
	 */

	public void setPassengerState(int id, int state) throws RemoteException;

	/**
	 *   Set hostess state.
	 *
	 *     @param state hostess state
	 *     @throws java.rmi.RemoteException if either the invocation of the remote method, or the communication with the registry
	 *     service fails
	 */

	public void setHostessState(int state) throws RemoteException;

	/**
	 *   Set pilot state.
	 *
	 *     @param state pilot state
	 *     @throws java.rmi.RemoteException if either the invocation of the remote method, or the communication with the registry
	 *     service fails
	 */

	public void setPilotState(int state) throws RemoteException;

	/**
	 * Write any text to the logg file
	 *
	 * @param toPrint text to be printed on the logg file
	 * @throws java.rmi.RemoteException if either the invocation of the remote method, or the communication with the registry
	 * service fails
	 */


	public void print(String toPrint) throws RemoteException;

	/**
	 * Write last status of the flight
	 * Air lift sum up
	 * 
	 * @throws java.rmi.RemoteException if either the invocation of the remote method, or the communication with the registry
	 * service fails
	 */

	public void printFinal() throws RemoteException;

	/**
	 * Increases the number of passengers in the queue
	 * 
	 * @throws java.rmi.RemoteException if either the invocation of the remote method, or the communication with the registry
	 * service fails
	 */

	public void addToQ() throws RemoteException;

	/**
	 * Decreases the number of passengers in the queue
	 * 
	 * @throws java.rmi.RemoteException if either the invocation of the remote method, or the communication with the registry
	 * service fails
	 */

	public void removeFromQ() throws RemoteException;

	/**
	 * Increases the number of passengers in flight
	 * 
	 * @throws java.rmi.RemoteException if either the invocation of the remote method, or the communication with the registry
	 * service fails
	 */

	public void addToF() throws RemoteException;

	/**
	 * Decreases the number of passengers in flight
	 * 
	 * @throws java.rmi.RemoteException if either the invocation of the remote method, or the communication with the registry
	 * service fails
	 */

	public void removeFromF() throws RemoteException;

	/**
	 * Increases the number of passengers that have arrived
	 * 
	 * @throws java.rmi.RemoteException if either the invocation of the remote method, or the communication with the registry
	 * service fails
	 */

	public void addToTotal() throws RemoteException;

	/**
	 * Total passengers that have arrived
	 * 
	 * @return total of passengers that have arrived
	 * @throws java.rmi.RemoteException if either the invocation of the remote method, or the communication with the registry
	 * service fails
	 */

	public int getTotal() throws RemoteException;


	/**
	 * Updates the flight information
	 *
	 * @param passengerNum number of passengers
	 * @throws java.rmi.RemoteException if either the invocation of the remote method, or the communication with the registry
	 * service fails
	 */

	public void updateFlightInfo(int passengerNum) throws RemoteException;

	/**
	 *   Operation server shutdown.
	 *
	 *   New operation.
	 *
	 *   @throws RemoteException if either the invocation of the remote method, or the communication with the registry
	 *                             service fails
	 */

	public void shutdown () throws RemoteException;
}
