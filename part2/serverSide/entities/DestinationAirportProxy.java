package serverSide.entities;

import commInfra.Message;
import commInfra.MessageType;
import commInfra.ServerCom;
import serverSide.sharedRegions.DestinationAirport;
import serverSide.sharedRegions.IntDestinationAirport;

public class DestinationAirportProxy implements IntDestinationAirport {

	/**
	 * Destination airport used to process the messages.
	 */

	private final DestinationAirport destAirport;

	/**
	 * Destination Airport Proxy constructor.
	 * @param destAirport destination airport to process the messages
	 */

	public DestinationAirportProxy(DestinationAirport destAirport){
		this.destAirport = destAirport;
	}

	/**
	 * Process and reply a message.
	 * @param inMessage message received
	 * @param scon communication channel
	 * @return message to be replied
	 */

	@Override
	public Message processAndReply(Message inMessage, ServerCom scon) {
		Message outMessage = null;

		switch(inMessage.getType()){
		case FLY_TO_DEPARTURE_POINT:{
			destAirport.flyToDeparturePoint();
			outMessage = new Message(MessageType.STATUS_OK);
			break;
		}
		}
		return outMessage;
	}
}
