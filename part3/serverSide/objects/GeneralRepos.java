package serverSide.objects;

import java.util.Objects;
import java.rmi.*;
import interfaces.*;
import serverSide.main.*;
import clientSide.entities.*;
import genclass.GenericIO;
import genclass.TextFile;

/**
 * Definition of the Repository
 *
 */

public class GeneralRepos implements GeneralReposInt {

	/**
	 *  Name of the logging file.
	 */

	private final String logFileName;

	/**
	 *  State of the passengers.
	 */

	private final int[] passengerStates;

	/**
	 *  State of the hostess.
	 */

	private final int[] hostessState;

	/**
	 *  State of the pilot.
	 */

	private final int[] pilotState;

	/**
	 *  Number of passengers in queue
	 */

	private int InQ = 0;

	/**
	 *  Number of passengers in flight
	 */

	private int InF = 0;

	/**
	 *  Number of passengers that have arrived
	 */

	private int PTAL = 0;

	/**
	 *  Flight number.
	 */

	private int flightNum = 0;

	/**
	 *  Array with information to pass to the repository
	 */

	private int [] flightInfo = new int[5];

	/**
	 *   Instantiation of a general repository object.
	 *
	 *   @param logFileName name of the logging file
	 *
	 */

	public GeneralRepos (String logFileName)
	{
		if ((logFileName == null) || Objects.equals (logFileName, ""))
			this.logFileName = "logger";
		else this.logFileName = logFileName;
		passengerStates = new int [SimulPar.K];
		for (int i = 0; i < SimulPar.K; i++)
			passengerStates[i] = PassengerStates.GOINGTOAIRPORT;

		hostessState = new int [SimulPar.H];
		hostessState[0] = HostessStates.WAITFORFLIGHT;

		pilotState = new int [SimulPar.P];
		pilotState[0] = PilotStates.ATTRANSFERGATE;
		reportInitialStatus ();
	}

	/**
	 *  Set passenger state.
	 *
	 *  @param id passenger id
	 *  @param state passenger state
	 *  @throws RemoteException if either the invocation of the remote method, or the communication with the registry
	 *  service fails
	 */

	@Override
	public synchronized void setPassengerState (int id, int state) throws RemoteException
	{
		passengerStates[id] = state;
		reportStatus ();
	}

	/**
	 *  Set hostess state.
	 *
	 *  @param state hostess state
	 *  @throws RemoteException if either the invocation of the remote method, or the communication with the registry
	 *  service fails
	 */

	@Override
	public synchronized void setHostessState(int state) throws RemoteException
	{
		hostessState[0] = state;
		reportStatus ();
	}

	/**
	 *  Set pilot state.
	 *
     *  @param state pilot state
     *  @throws RemoteException if either the invocation of the remote method, or the communication with the registry
	 *  service fails
	 */
	
	@Override
	public synchronized void setPilotState (int state) throws RemoteException
	{
		pilotState[0] = state;
		reportStatus ();
	}

	/**
	 *  Write the header to the logging file.
	 *
	 *  The pilot is at the the transfer gate
	 *  The hostess is waiting for the next flight
	 *  The passenger is going to the airport
	 *  Internal operation.
	 */

	public void reportInitialStatus ()
	{
		TextFile log = new TextFile ();                      // instantiation of a text file handler

		if (!log.openForWriting (".", logFileName))
		{ GenericIO.writelnString ("The operation of creating the file " + logFileName + " failed!");
		System.exit (1);
		}
		log.writelnString ("                Airlift - Description of the internal state");
		String str = " PT   HT ";
		for(int i = 0; i < 21; i++) {
			if(i < 10) {
				str += "   P0" + i;
			} else {
				str += "   P" + i;
			}
		}
		log.writelnString(str + "  InQ  InF  PTAL");
		if (!log.close ())
		{ GenericIO.writelnString ("The operation of closing the file " + logFileName + " failed!");
		System.exit (1);
		}
		reportStatus ();
	}

	/**
	 *  Write a state line at the end of the logging file.
	 *
	 *  The current state of the pilot, hostess and passengers is organized in a line to be printed.
	 *  Internal operation.
	 */

	private void reportStatus ()
	{
		TextFile log = new TextFile ();                      // instantiation of a text file handler

		String lineStatus = "";                              // state line to be printed

		if (!log.openForAppending (".", logFileName))
		{ GenericIO.writelnString ("The operation of opening for appending the file " + logFileName + " failed!");
		System.exit (1);
		}

		switch (pilotState[0])
		{ case PilotStates.ATTRANSFERGATE:  lineStatus += "ATRG ";
		break;
		case PilotStates.READYFORBOARDING: lineStatus += "RDFB ";
		break;
		case PilotStates.WAITINGFORBOARDING:      lineStatus += "WTFB ";
		break;
		case PilotStates.FLYINGFORWARD:    lineStatus += "FLFW ";
		break;
		case PilotStates.DEBOARDING:      lineStatus += "DRPP ";
		break;
		case PilotStates.FLYINGBACK:    lineStatus += "FLBK ";
		break;
		}

		switch (hostessState[0])
		{ case HostessStates.WAITFORFLIGHT:  lineStatus += " WTFL ";
		break;
		case HostessStates.WAITFORPASSENGER: lineStatus += " WTPS ";
		break;
		case HostessStates.CHECKPASSENGER:      lineStatus += " CKPS ";
		break;
		case HostessStates.READYTOFLY:    lineStatus += " RDTF ";
		break;
		}

		for (int i = 0; i < SimulPar.K; i++)
			switch (passengerStates[i])
			{ case PassengerStates.GOINGTOAIRPORT:   lineStatus += " GTAP ";
			break;
			case PassengerStates.INQUEUE: 	  lineStatus += " INQE ";
			break;

			case PassengerStates.INFLIGHT: 	lineStatus += " INFL ";
			break;
			case PassengerStates.ATDESTINATION: 	lineStatus += " ATDS ";
			break;
			}

		lineStatus += "  " + InQ + "    " + InF + "    " + PTAL;

		log.writelnString (lineStatus);
		if (!log.close ())
		{ GenericIO.writelnString ("The operation of closing the file " + logFileName + " failed!");
		System.exit (1);
		}
	}


	/**
	 * Write any text to the logg file
	 *
	 * @param toPrint text to be printed on the logg file
	 */

	public synchronized void print(String toPrint) {

		TextFile log = new TextFile ();                      // instantiation of a text file handler

		if (!log.openForAppending (".", logFileName))
		{
			GenericIO.writelnString ("The operation of opening for appending the file " + logFileName + " failed!");
			System.exit (1);
		}

		log.writelnString (toPrint);
		if (!log.close ())
		{
			GenericIO.writelnString ("The operation of closing the file " + logFileName + " failed!");
			System.exit (1);
		}
	}

	/**
	 * Write last status of the flight
	 * Air lift sum up
	 */

	public synchronized void printFinal() {
		TextFile log = new TextFile ();                      // instantiation of a text file handler

		if (!log.openForAppending (".", logFileName))
		{
			GenericIO.writelnString ("The operation of opening for appending the file " + logFileName + " failed!");
			System.exit (1);
		}

		log.writelnString ("\nAirlift sum up:");

		int i = 1;
		for (int numP : flightInfo) {
			if(numP != 0) {
				log.writelnString("Flight " + i + " transported " + numP + " passengers");
				i++;
			}
		}

		if (!log.close ())
		{
			GenericIO.writelnString ("The operation of closing the file " + logFileName + " failed!");
			System.exit (1);
		}
	}

	/**
	 * Increases the number of passengers in the queue
	 * 
	 * @throws RemoteException if either the invocation of the remote method, or the communication with the registry
	 * service fails
	 */

	@Override
	public void addToQ() throws RemoteException {
		this.InQ += 1;
	}

	/**
	 * Decreases the number of passengers in the queue
	 * 
	 * @throws RemoteException if either the invocation of the remote method, or the communication with the registry
	 * service fails
	 */

	@Override
	public void removeFromQ() throws RemoteException {
		this.InQ -= 1;
	}

	/**
	 * Increases the number of passengers in flight
	 * 
	 * @throws RemoteException if either the invocation of the remote method, or the communication with the registry
	 * service fails
	 */

	@Override
	public void addToF() throws RemoteException {
		this.InF += 1;
	}

	/**
	 * Decreases the number of passengers in flight
	 * 
	 * @throws RemoteException if either the invocation of the remote method, or the communication with the registry
	 * service fails
	 */

	@Override
	public void removeFromF() throws RemoteException {
		this.InF -= 1;
	}

	/**
	 * Increases the number of passengers that have arrived
	 * 
	 * @throws RemoteException if either the invocation of the remote method, or the communication with the registry
	 * service fails
	 */

	@Override
	public void addToTotal() throws RemoteException {
		this.PTAL += 1;
	}

	/**
	 * Total passengers that have arrived
	 * 
	 * @return total of passengers that have arrived
	 * @throws RemoteException if either the invocation of the remote method, or the communication with the registry
	 * service fails
	 */

	@Override
	public int getTotal() throws RemoteException {
		return PTAL;
	}

	/**
	 * Updates the flight information
	 *
	 * @param passengNum number of passengers
	 * @throws RemoteException if either the invocation of the remote method, or the communication with the registry
	 * service fails
	 */

	@Override
	public void updateFlightInfo(int passengNum) throws RemoteException {
		flightInfo[this.flightNum] = passengNum;
		this.flightNum++;
	}

	/**
	 *   Operation server shutdown.
	 *
	 *   New operation.
	 *
	 *   @throws RemoteException if either the invocation of the remote method, or the communication with the registry
	 *   service fails
	 */

	@Override
	public synchronized void shutdown () throws RemoteException
	{
		MainRepos.shutdown ();
		notifyAll();
	}
}
