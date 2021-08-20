package serverSide.sharedRegions;

import clientSide.entities.PilotStates;
import clientSide.stubs.GeneralReposStub;
import genclass.GenericIO;
import serverSide.main.MainDepartureAirport;
import serverSide.main.MainDestinationAirport;

/**
 * Definition of the Destination Airport
 *
 */

public class DestinationAirport {
	
	/**
	 *   Reference to the general repository.
     */
	
	private final GeneralReposStub repos;
	
	/**
     *  Flight number
     */
	
	private int flightNum = 0;
	
	/**
     *   Instantiation of the destination airport
     *
     *     @param repos general repository
     */
	
	public DestinationAirport(GeneralReposStub repos)
	{
		this.repos = repos;
	}
	
	/**
	 *  Operation fly to departure point
	 *
	 *  It is called by the pilot after deboarding.
	 *
	 */
	
	public synchronized void flyToDeparturePoint() 
	{
    	GenericIO.writelnString("Plane took off");
    	flightNum++;
    	repos.print("\nFlight " + flightNum + ": returning");
		repos.setPilotState(PilotStates.FLYINGBACK);
		GenericIO.writelnString("End of flight " + flightNum + "!");
    }
	
	/**
     * Terminate the Destination airport service.
     */
	
	public void serviceEnd() {
		MainDestinationAirport.serviceEnd = true;
	}
}
