package clientSide.stubs;

import commInfra.ClientCom;
import commInfra.Message;
import commInfra.MessageType;

/**
 * Definition of the Destination Airport
 *
 */

public class DestinationAirportStub {

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

	public DestinationAirportStub(String hostName, int port)
	{
		serverHostName = hostName;
		serverPortNumb = port;
	}

	/**
	 *  Operation fly to departure point
	 *
	 *  It is called by the pilot after all passengers deboard.
	 */

	public synchronized void flyToDeparturePoint() 
	{
		ClientCom com = new ClientCom (serverHostName, serverPortNumb);

		while(!com.open()){
			try {
				Thread.currentThread ();
				Thread.sleep ((10));
			} catch (InterruptedException ex) {
			}
		}

		Message msg = new Message(MessageType.FLY_TO_DEPARTURE_POINT);
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
