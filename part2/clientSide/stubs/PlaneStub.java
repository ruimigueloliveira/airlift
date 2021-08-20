package clientSide.stubs;

import commInfra.ClientCom;
import commInfra.Message;
import commInfra.MessageType;

/**
 * Definition of the Plane
 *
 */

public class PlaneStub {

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

	public PlaneStub(String hostName, int port)
	{
		serverHostName = hostName;
		serverPortNumb = port;
	}

	/**
	 *  Operation board the plane
	 *  
	 *  It is called by the passenger after showing documents
	 */

	public void boardThePlane(int id) {
		ClientCom com = new ClientCom (serverHostName, serverPortNumb);

		while(!com.open()){
			try {
				Thread.currentThread ();
				Thread.sleep ((10));
			} catch (InterruptedException ex) {
			}
		}

		Message msg = new Message(MessageType.BOARD_THE_PLANE, id);
		com.writeObject(msg);
		Message inMessage = (Message) com.readObject();
		com.close ();
	}

	/**
	 *  Operation fly to destination point
	 *  
	 *  It is called by the pilot after all passengers board the plane.
	 */

	public void flyToDestinationPoint() {
		ClientCom com = new ClientCom (serverHostName, serverPortNumb);

		while(!com.open()){
			try {
				Thread.currentThread ();
				Thread.sleep ((10));
			} catch (InterruptedException ex) {
			}
		}

		Message msg = new Message(MessageType.FLY_TO_DESTINATION_POINT);
		com.writeObject(msg);
		Message inMessage = (Message) com.readObject();
		com.close ();
	}

	/**
	 *  Operation wait for end of flight
	 *  
	 *  It is called by the passenger after entering the plane to wait for the end of the flight.
	 */

	public synchronized void waitForEndOfFlight(int id) {
		ClientCom com = new ClientCom (serverHostName, serverPortNumb);

		while(!com.open()){
			try {
				Thread.currentThread ();
				Thread.sleep ((10));
			} catch (InterruptedException ex) {
			}
		}

		Message msg = new Message(MessageType.WAIT_FOR_END_OF_FLIGHT, id);
		com.writeObject(msg);
		Message inMessage = (Message) com.readObject();
		com.close ();
	}

	/**
	 *  Operation announce arrival
	 *  
	 *  It is called by the pilot after reaching the destination.
	 */

	public synchronized void announceArrival() {
		ClientCom com = new ClientCom (serverHostName, serverPortNumb);

		while(!com.open()){
			try {
				Thread.currentThread ();
				Thread.sleep ((10));
			} catch (InterruptedException ex) {
			}
		}

		Message msg = new Message(MessageType.ANNOUNCE_ARRIVAL);
		com.writeObject(msg);
		Message inMessage = (Message) com.readObject();
		com.close ();
	}


	/**
	 *  Operation leave the plane
	 *  
	 *  It is called by the passenger after being in flight.
	 */

	public synchronized void leaveThePlane(int id) {
		ClientCom com = new ClientCom (serverHostName, serverPortNumb);

		while(!com.open()){
			try {
				Thread.currentThread ();
				Thread.sleep ((10));
			} catch (InterruptedException ex) {
			}
		}

		Message msg = new Message(MessageType.LEAVE_THE_PLANE, id);
		com.writeObject(msg);
		Message inMessage = (Message) com.readObject();
		com.close ();
	}

	/**
	 *  Operation service end.
	 *
	 *  It is called by each thread to end the service.
	 */

	public void serviceEnd(){
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
