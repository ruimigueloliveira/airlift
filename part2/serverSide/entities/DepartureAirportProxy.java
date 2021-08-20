package serverSide.entities;

import commInfra.Message;
import commInfra.MessageType;
import commInfra.ServerCom;
import serverSide.sharedRegions.DepartureAirport;
import serverSide.sharedRegions.IntDepartureAirport;

public class DepartureAirportProxy implements IntDepartureAirport {

	/**
	 * Destination airport used to process the messages.
	 */

	private final DepartureAirport departAirport;

	/**
	 * Destination Airport Proxy constructor.
	 * @param destAirport destination airport to process the messages
	 */

	public DepartureAirportProxy(DepartureAirport departAirport){
		this.departAirport = departAirport;
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
		case PARK_AT_TRANSFER_GATE:{
			outMessage = new Message(MessageType.PARK_AT_TRANSFER_GATE, departAirport.parkAtTransferGate());
			break;
		}
		case INFORM_PLANE_READY_FOR_BOARDING:{
			departAirport.informPlaneReadyForBoarding();
			outMessage = new Message(MessageType.STATUS_OK);
			break;

		}
		case WAIT_FOR_ALL_IN_BOARD:{
			departAirport.waitForAllInBoard();
			outMessage = new Message(MessageType.STATUS_OK);
			break;

		}
		case PREPARE_FOR_PASS_BOARDING:{
			departAirport.prepareForPassBoarding();
			outMessage = new Message(MessageType.STATUS_OK);
			break;

		}
		case WAIT_IN_QUEUE:{
			departAirport.waitInQueue(inMessage.getPassengerID());
			outMessage = new Message(MessageType.STATUS_OK);
			break;

		}
		case WAIT_FOR_NEXT_PASSENGER:{
			outMessage = new Message(MessageType.WAIT_FOR_NEXT_PASSENGER, departAirport.waitForNextPassenger());
			break;

		}
		case SHOW_DOCUMENTS:{
			departAirport.showDocuments(inMessage.getPassengerID());
			outMessage = new Message(MessageType.STATUS_OK);
			break;

		}
		case CHECK_DOCUMENTS:{
			departAirport.checkDocuments(inMessage.getPassengerID());
			outMessage = new Message(MessageType.STATUS_OK);
			break;
		}
		case INFORM_PLANE_READY_TO_TAKE_OFF:{
			departAirport.informPlaneReadyToTakeOff();
			outMessage = new Message(MessageType.STATUS_OK);
			break;

		}
		case ALL_PASSENG_LEFT:{
			outMessage = new Message(MessageType.RETURN_ALL_PASSENG_LEFT, departAirport.allPassengLeft());
			break;
		}
		case WAIT_FOR_NEXT_FLIGHT:{
			departAirport.waitForNextFlight();
			outMessage = new Message(MessageType.STATUS_OK);
			break;
		}
		case SERVICE_END:{
			departAirport.serviceEnd();
			outMessage = new Message(MessageType.STATUS_OK);
			break;
		}
		}
		return outMessage;
	}	
}
