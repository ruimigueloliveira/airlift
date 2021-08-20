package clientSide.stubs;

import commInfra.ClientCom;
import commInfra.Message;
import commInfra.MessageType;

/**
 * Definition of the Repository
 *
 */

public class GeneralReposStub {

	/**
	 * Name of the computational system where it is located the server.
	 */

	private final String serverHostName;

	/**
	 * Number of server listening port.
	 */

	private final int serverPortNumb;

	/**
	 *   Instantiation of a general repository object.
	 *
	 *   @param logFileName name of the logging file
	 *
	 */

	public GeneralReposStub (String hostName, int port)
	{
		serverHostName = hostName;
		serverPortNumb = port;
	}

	/**
	 *   Set passenger state.
	 *
	 *     @param id passenger id
	 *     @param state passenger state
	 */

	public synchronized void setPassengerState (int id, int state)
	{
		ClientCom com = new ClientCom(serverHostName, serverPortNumb);

		while(!com.open()) {
			try {
				Thread.currentThread();
				Thread.sleep(10);
			} catch (InterruptedException e) {

			}
		}

		Message msg = new Message(MessageType.SET_PASSENGER_STATE, id, state);
		com.writeObject(msg);
		Message inMessage = (Message) com.readObject();
		com.close();
	}

	/**
	 *   Set hostess state.
	 *
	 *     @param state hostess state
	 */

	public synchronized void setHostessState (int state)
	{
		ClientCom com = new ClientCom(serverHostName, serverPortNumb);

		while(!com.open()) {
			try {
				Thread.currentThread();
				Thread.sleep(10);
			} catch (InterruptedException e) {

			}
		}
		Message msg = new Message(MessageType.SET_HOSTESS_STATE, state);
		com.writeObject(msg);
		Message inMessage = (Message) com.readObject();
		com.close();
	}

	/**
	 *   Set pilot state.
	 *
	 *     @param state pilot state
	 */

	public synchronized void setPilotState (int state)
	{
		ClientCom com = new ClientCom(serverHostName, serverPortNumb);

		while(!com.open()) {
			try {
				Thread.currentThread();
				Thread.sleep(10);
			} catch (InterruptedException e) {

			}
		}
		Message msg = new Message(MessageType.SET_PILOT_STATE, state);
		com.writeObject(msg);
		Message inMessage = (Message) com.readObject();
		com.close();
	}
	
	/**
	 *   Print to log file.
	 *
	 *     @param toPrint text to print on the logger
	 */

	public synchronized void print (String toPrint)
	{
		ClientCom com = new ClientCom(serverHostName, serverPortNumb);

		while(!com.open()) {
			try {
				Thread.currentThread();
				Thread.sleep(10);
			} catch (InterruptedException e) {

			}
		}
		Message msg = new Message(MessageType.PRINT, toPrint);
		com.writeObject(msg);
		Message inMessage = (Message) com.readObject();
		com.close();
	}

	/**
	 * Increases the number of passengers in the queue
	 */

	public void addToQ() {
		ClientCom com = new ClientCom(serverHostName, serverPortNumb);

		while(!com.open()) {
			try {
				Thread.currentThread();
				Thread.sleep(10);
			} catch (InterruptedException e) {

			}
		}
		Message msg = new Message(MessageType.ADD_TO_Q);
		com.writeObject(msg);
		Message inMessage = (Message) com.readObject();
		com.close();
	}

	/**
	 * Decreases the number of passengers in the queue
	 */

	public void removeFromQ() {
		ClientCom com = new ClientCom(serverHostName, serverPortNumb);

		while(!com.open()) {
			try {
				Thread.currentThread();
				Thread.sleep(10);
			} catch (InterruptedException e) {

			}
		}
		Message msg = new Message(MessageType.REMOVE_FROM_Q);
		com.writeObject(msg);
		Message inMessage = (Message) com.readObject();
		com.close();
	}

	/**
	 * Increases the number of passengers in flight
	 */

	public void addToF() {
		ClientCom com = new ClientCom(serverHostName, serverPortNumb);

		while(!com.open()) {
			try {
				Thread.currentThread();
				Thread.sleep(10);
			} catch (InterruptedException e) {

			}
		}
		Message msg = new Message(MessageType.ADD_TO_F);
		com.writeObject(msg);
		Message inMessage = (Message) com.readObject();
		com.close();
	}

	/**
	 * Decreases the number of passengers in flight
	 */

	public void removeFromF() {
		ClientCom com = new ClientCom(serverHostName, serverPortNumb);

		while(!com.open()) {
			try {
				Thread.currentThread();
				Thread.sleep(10);
			} catch (InterruptedException e) {

			}
		}
		Message msg = new Message(MessageType.REMOVE_FROM_F);
		com.writeObject(msg);
		Message inMessage = (Message) com.readObject();
		com.close();
	}

	/**
	 * Increases the number of passengers that have arrived
	 */

	public void addToTotal() {
		ClientCom com = new ClientCom(serverHostName, serverPortNumb);

		while(!com.open()) {
			try {
				Thread.currentThread();
				Thread.sleep(10);
			} catch (InterruptedException e) {

			}
		}
		Message msg = new Message(MessageType.ADD_TO_TOTAL);
		com.writeObject(msg);
		Message inMessage = (Message) com.readObject();
		com.close();
	}

	/**
	 * @return total of passengers that have arrived
	 */

	public int getTotal() {
		ClientCom com = new ClientCom(serverHostName, serverPortNumb);

		while(!com.open()) {
			try {
				Thread.currentThread();
				Thread.sleep(10);
			} catch (InterruptedException e) {

			}
		}
		Message msg = new Message(MessageType.GET_TOTAL);
		com.writeObject(msg);
		Message inMessage = (Message) com.readObject();
		com.close();
		return inMessage.getTotal();
	}

	/**
	 * Updates the flight information
	 *
	 * @param passengNum number of passengers
	 */

	public void updateFlightInfo(int passengNum) {
		ClientCom com = new ClientCom(serverHostName, serverPortNumb);

		while(!com.open()) {
			try {
				Thread.currentThread();
				Thread.sleep(10);
			} catch (InterruptedException e) {

			}
		}
		Message msg = new Message(MessageType.UPDATE_FLIGHT_INFO, passengNum);
		com.writeObject(msg);
		Message inMessage = (Message) com.readObject();
		com.close();
	}
	
	/**
	 * Final print of the logger
	 */

	public void printFinal() {
		ClientCom com = new ClientCom(serverHostName, serverPortNumb);

		while(!com.open()) {
			try {
				Thread.currentThread();
				Thread.sleep(10);
			} catch (InterruptedException e) {

			}
		}
		Message msg = new Message(MessageType.PRINT_FINAL);
		com.writeObject(msg);
		Message inMessage = (Message) com.readObject();
		com.close();
	}
	
	/**
	 *  Operation service end.
	 *
	 *  It is called by each thread to end the service.
	 */

	public void serviceEnd() {
		ClientCom com = new ClientCom (serverHostName, serverPortNumb);

		while(!com.open()){
			try {
				Thread.currentThread ();
				Thread.sleep ((10));
			} catch (InterruptedException ex) {
			}
		}
		Message msg = new Message(MessageType.SERVICE_END);
		com.writeObject(msg);
		Message inMessage = (Message) com.readObject();
		com.close ();
	}
}
