package serverSide.sharedRegions;

import commInfra.Message;
import commInfra.ServerCom;

/**
 * Service Provider implementation.
 * Processes and replies messages accordingly to the internal implementation
 * of a shared region.
 */
public class ServiceProviderDestination extends Thread {

	/**
     * Communication channel with the server.
     */
    private final ServerCom com;
    
    /**
     * Shared region implementation.
     */
    private final IntDestinationAirport intSR;
    
    /**
     * Service Provider constructor.
     * @param com communication channel with the server.
     * @param intSR shared region.
     */
    public ServiceProviderDestination(ServerCom com, IntDestinationAirport intSR){
        this.com = com;
        this.intSR = intSR;
    }
    
    /**
     * Lifecycle of the service provider.
     */
    @Override
    public void run(){
        /**
         * Read object from the communication channel.
         */
        Message inMessage = (Message) com.readObject();
        
        /**
         * Process and reply request.
         */
        Message outMessage = intSR.processAndReply(inMessage, com);
        /**
         * Send reply and close communication channel.
         */
        com.writeObject(outMessage);
        com.close();
    }
}
