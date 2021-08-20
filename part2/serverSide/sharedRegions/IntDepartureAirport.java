package serverSide.sharedRegions;

import commInfra.Message;
import commInfra.ServerCom;

public interface IntDepartureAirport {

	/**
	 * Process and reply a message
	 * @param inMessage message to be processed
	 * @param scon communication channel
	 * @return message to be replied
	 */

	public Message processAndReply(Message inMessage, ServerCom scon);
}
