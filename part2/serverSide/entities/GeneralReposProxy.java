package serverSide.entities;

import commInfra.Message;
import commInfra.MessageType;
import commInfra.ServerCom;
import serverSide.sharedRegions.GeneralRepos;
import serverSide.sharedRegions.IntGeneralRepos;

public class GeneralReposProxy implements IntGeneralRepos {

	/**
	 * General repository used to process the messages.
	 */

	private final GeneralRepos generalRepos;

	/**
	 * General Repository Proxy constructor.
	 * @param generalRepos general repository to process the messages
	 */

	public GeneralReposProxy(GeneralRepos generalRepos){
		this.generalRepos = generalRepos;
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
		case PRINT:{
			generalRepos.print(inMessage.toPrint());
			outMessage = new Message(MessageType.STATUS_OK);
			break;

		}
		case PRINT_FINAL:{
			generalRepos.printFinal();
			outMessage = new Message(MessageType.STATUS_OK);
			break;

		}
		case SET_PASSENGER_STATE:{
			generalRepos.setPassengerState(inMessage.getPassengerID(), inMessage.getPassengerState());
			outMessage = new Message(MessageType.STATUS_OK);
			break;

		}
		case SET_HOSTESS_STATE:{
			generalRepos.setHostessState(inMessage.getHostessState());
			outMessage = new Message(MessageType.STATUS_OK);
			break;

		}
		case SET_PILOT_STATE:{
			generalRepos.setPilotState(inMessage.getPilotState());
			outMessage = new Message(MessageType.STATUS_OK);
			break;

		}
		case ADD_TO_Q:{
			generalRepos.addToQ();
			outMessage = new Message(MessageType.STATUS_OK);
			break;

		}
		case REMOVE_FROM_Q:{
			generalRepos.removeFromQ();
			outMessage = new Message(MessageType.STATUS_OK);
			break;

		}
		case ADD_TO_F:{
			generalRepos.addToF();
			outMessage = new Message(MessageType.STATUS_OK);
			break;

		}
		case REMOVE_FROM_F:{
			generalRepos.removeFromF();
			outMessage = new Message(MessageType.STATUS_OK);
			break;

		}
		case ADD_TO_TOTAL:{
			generalRepos.addToTotal();
			outMessage = new Message(MessageType.STATUS_OK);
			break;

		}
		case GET_TOTAL:{
			outMessage = new Message(MessageType.GET_TOTAL, generalRepos.getTotal());
			break;

		}
		case UPDATE_FLIGHT_INFO:{
			generalRepos.updateFlightInfo(inMessage.getPassengersOnPlane());
			outMessage = new Message(MessageType.STATUS_OK);
			break;

		}
		}
		return outMessage;
	}
}