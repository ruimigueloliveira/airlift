package clientSide.stubs;

import commInfra.ClientCom;
import commInfra.Message;
import commInfra.MessageType;

/**
 * Definition of the Departure Airport
 *
 */

public class DepartureAirportStub {


	/**
	 * Name of the computational system where it is located the server.
	 */
	
	private final String serverHostName;

	/**
	 * Number of server listening port.
	 */
	
	private final int serverPortNumb;

	/**
	 *  Stub instantiation.
	 *
	 *    @param hostName Name of the computational system where it is located the server.
	 *    @param port Number of server listening port.
	 */
	
	public DepartureAirportStub(String hostName, int port)
	{
		serverHostName = hostName;
		serverPortNumb = port;
	}


	/**
	 *  Operation park at transfer gate.
	 *
	 *  It is called by the pilot when the plane is parked at the transfer gate.
	 */

	public synchronized boolean parkAtTransferGate() {
		ClientCom com = new ClientCom (serverHostName, serverPortNumb);

		while(!com.open()){
			try {
				Thread.currentThread ();
				Thread.sleep ((10));
			} catch (InterruptedException ex) {
			}
		}
		Message msg = new Message(MessageType.PARK_AT_TRANSFER_GATE);
		com.writeObject(msg);
		Message inMessage = (Message) com.readObject();
		com.close ();
		return inMessage.lastFlight();
	}

	/**
	 *  Operation inform plane ready for boarding.
	 *
	 *  It is called by the pilot when the plane is ready for boarding.
	 */

	public synchronized void informPlaneReadyForBoarding() {
		ClientCom com = new ClientCom (serverHostName, serverPortNumb);

		while(!com.open()){
			try {
				Thread.currentThread ();
				Thread.sleep ((10));
			} catch (InterruptedException ex) {
			}
		}

		Message msg = new Message(MessageType.INFORM_PLANE_READY_FOR_BOARDING);
		com.writeObject(msg);
		Message inMessage = (Message) com.readObject();
		com.close ();
	}

	/**
	 *  Operation wait for all in board.
	 *
	 *  It is called by the pilot when he is waiting for the passengers to board the plane.
	 */

	public synchronized void waitForAllInBoard() {
		ClientCom com = new ClientCom (serverHostName, serverPortNumb);

		while(!com.open()){
			try {
				Thread.currentThread ();
				Thread.sleep ((10));
			} catch (InterruptedException ex) {
			}
		}

		Message msg = new Message(MessageType.WAIT_FOR_ALL_IN_BOARD);
		com.writeObject(msg);
		Message inMessage = (Message) com.readObject();
		com.close ();
	}

	/**
	 *  Operation prepare for passing boarding.
	 *
	 *  It is called by the hostess to start the boarding process.
	 */

	public synchronized void prepareForPassBoarding() {
		ClientCom com = new ClientCom (serverHostName, serverPortNumb);

		while(!com.open()){
			try {
				Thread.currentThread ();
				Thread.sleep ((10));
			} catch (InterruptedException ex) {

			}
		}  

		Message msg = new Message(MessageType.PREPARE_FOR_PASS_BOARDING);
		com.writeObject(msg);
		Message inMessage = (Message) com.readObject();
		com.close ();
	}

	/**
	 *  Operation wait in queue.
	 *
	 *  It is called by each passenger when they go to wait in the queue.
	 */

	public synchronized void waitInQueue(int id) {    
		ClientCom com = new ClientCom (serverHostName, serverPortNumb);

		while(!com.open()){
			try {
				Thread.currentThread ();
				Thread.sleep ((10));
			} catch (InterruptedException ex) {
			}
		}

		Message msg = new Message(MessageType.WAIT_IN_QUEUE, id);
		com.writeObject(msg);
		Message inMessage = (Message) com.readObject();
		com.close ();
	}

	/**
	 *  Operation wait for next passenger.
	 *
	 *  It is called by the hostess when she's ready to check the next passenger.
	 *  
	 *  @return passenger id or -1 if the plane is ready to take off.
	 */

	public synchronized int waitForNextPassenger() {
		ClientCom com = new ClientCom (serverHostName, serverPortNumb);

		while(!com.open()){
			try {
				Thread.currentThread ();
				Thread.sleep ((10));
			} catch (InterruptedException ex) {
			}
		}

		Message msg = new Message(MessageType.WAIT_FOR_NEXT_PASSENGER);
		com.writeObject(msg);
		Message inMessage = (Message) com.readObject();
		com.close ();
		return inMessage.getPassengerID();
	}

	/**
	 *  Operation show documents.
	 *
	 *  It is called by the passengers when they show the documents to the hostess.
	 */

	public synchronized void showDocuments(int id) {
		ClientCom com = new ClientCom (serverHostName, serverPortNumb);

		while(!com.open()){
			try {
				Thread.currentThread ();
				Thread.sleep ((10));
			} catch (InterruptedException ex) {
			}
		}

		Message msg = new Message(MessageType.SHOW_DOCUMENTS, id);
		com.writeObject(msg);
		Message inMessage = (Message) com.readObject();
		com.close ();
	}

	/**
	 *  Operation check documents.
	 *
	 *  It is called by the hostess when she checks the passenger's documents, 
	 *  and also makes sure it's the correct passenger.
	 *
	 * @param passengerID id of the passenger
	 */

	public synchronized void checkDocuments(int passengerID) {
		ClientCom com = new ClientCom (serverHostName, serverPortNumb);

		while(!com.open()){
			try {
				Thread.currentThread ();
				Thread.sleep ((10));
			} catch (InterruptedException ex) {
			}
		}

		Message msg = new Message(MessageType.CHECK_DOCUMENTS, passengerID);
		com.writeObject(msg);
		Message inMessage = (Message) com.readObject();
		com.close ();
	}

	/**
	 *  Operation inform plane ready to take off.
	 *
	 *  It is called by the hostess when the plane has a valid number of passengers
	 *  and is ready to take off.
	 */

	public synchronized void informPlaneReadyToTakeOff() {
		ClientCom com = new ClientCom (serverHostName, serverPortNumb);

		while(!com.open()){
			try {
				Thread.currentThread ();
				Thread.sleep ((10));
			} catch (InterruptedException ex) {
			}
		}

		Message msg = new Message(MessageType.INFORM_PLANE_READY_TO_TAKE_OFF);
		com.writeObject(msg);
		Message inMessage = (Message) com.readObject();
		com.close ();
	}

	/**
	 *  Operation wait for next flight.
	 *
	 *  It is called by the hostess when the plane has taken off and
	 *  she is now waiting for the next flight.
	 */

	public synchronized void waitForNextFlight() {
		ClientCom com = new ClientCom (serverHostName, serverPortNumb);

		while(!com.open()){
			try {
				Thread.currentThread ();
				Thread.sleep ((10));
			} catch (InterruptedException ex) {
			}
		}

		Message msg = new Message(MessageType.WAIT_FOR_NEXT_FLIGHT);
		com.writeObject(msg);
		Message inMessage = (Message) com.readObject();
		com.close ();
	}

	/**
	 *  Operation all passengers left.
	 *
	 *  It is called by the hostess and the pilot to check if there are still passengers to board.
	 *  
	 *  @return true if all passengers have left the departing airport.
	 */

	public synchronized boolean allPassengLeft() {
		ClientCom com = new ClientCom (serverHostName, serverPortNumb);

		while(!com.open()){
			try {
				Thread.currentThread ();
				Thread.sleep ((10));
			} catch (InterruptedException ex) {
			}
		}

		Message msg = new Message(MessageType.ALL_PASSENG_LEFT);
		com.writeObject(msg);
		Message inMessage = (Message) com.readObject();
		com.close ();
		return inMessage.allPassengLeft();
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