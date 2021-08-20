package serverSide.entities;

import commInfra.Message;
import commInfra.MessageType;
import commInfra.ServerCom;
import serverSide.sharedRegions.IntPlane;
import serverSide.sharedRegions.Plane;

public class PlaneProxy implements IntPlane {

	/**
	 * Plane used to process the messages.
	 */

	private final Plane plane;

	/**
	 * Plane Proxy constructor.
	 * @param plane plane to process the messages
	 */
	public PlaneProxy(Plane plane){
		this.plane = plane;
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
		case BOARD_THE_PLANE:{
			plane.boardThePlane(inMessage.getPassengerID());
			outMessage = new Message(MessageType.STATUS_OK);
			break;

		}
		case FLY_TO_DESTINATION_POINT:{
			plane.flyToDestinationPoint();
			outMessage = new Message(MessageType.STATUS_OK);
			break;

		}
		case WAIT_FOR_END_OF_FLIGHT:{
			plane.waitForEndOfFlight();
			outMessage = new Message(MessageType.STATUS_OK);
			break;

		}
		case ANNOUNCE_ARRIVAL:{
			plane.announceArrival();
			outMessage = new Message(MessageType.STATUS_OK);
			break;

		}
		case LEAVE_THE_PLANE:{
			plane.leaveThePlane();
			outMessage = new Message(MessageType.STATUS_OK);
			break;

		}
		}
		return outMessage;
	}	
}
