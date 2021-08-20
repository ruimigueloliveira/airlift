package serverSide.sharedRegions;

import clientSide.entities.HostessStates;
import clientSide.entities.PassengerStates;
import clientSide.entities.PilotStates;
import clientSide.stubs.GeneralReposStub;
import commInfra.MemException;
import commInfra.MemFIFO;
import genclass.GenericIO;
import serverSide.main.MainDepartureAirport;
import serverSide.main.MainDestinationAirport;
import serverSide.main.MainPlane;
import serverSide.main.MainRepos;
import serverSide.main.SimulPar;

/**
 * Definition of the Departure Airport
 *
 */

public class DepartureAirport {

	/**
     *  Number of passengers in queue.
     */

	private int nQueue;


	/**
	 *   Reference to the general repository stub.
     */

    private final GeneralReposStub repos;

    /**
     *   Passengers in queue.
     */

    private MemFIFO<Integer> inQueue;

    /**
     *   Array that checks if each passenger has shown their documents.
     */

    private boolean [] documentsShowed = new boolean[SimulPar.K];

    /**
     *   Array that checks each passengers state.
     */

    private int [] stateID = new int[SimulPar.K];

    /**
     *   Total passengers that are on the plane.
     */

    private int passengersOnPlane = 0;

    /**
     *   Number of passengers that still haven't departured.
     */

    private int remainingPassengers = SimulPar.K;

    /**
     *   Id of the passenger that is being checked for his documents.
     */

    private int actualId = -1;

    /**
     *   Number of the current flight.
     */

    private int flightNum = 0;

    /**
     *   true if the plane is ready to take off.
     */

    private boolean readyToTakeOff = false;

    /**
     *   true if the plane is ready for boarding.
     */

    private boolean readyForBoarding = false;

    /**
     *   true if it's the last flight.
     */

    private boolean lastFlight = false;

    /**
     *   true if hostess can inform the pilot that the plane is ready to take off.
     */

    private boolean informPilot = false;

    /**
     *  Departure airport instantiation.
     *
     *    @param repos reference to the general repository
     */

	public DepartureAirport(GeneralReposStub repos)
	{
		nQueue = 0;
		try
		{
			inQueue = new MemFIFO<> (new Integer [SimulPar.K]);
		} catch (MemException e) {
			GenericIO.writelnString ("Instantiation of waiting FIFO failed: " + e.getMessage ());
      	  	inQueue = null;
      	  	System.exit (1);
		}
		this.repos = repos;
	}

	/**
	 *  Operation park at transfer gate.
	 *
	 *  It is called by the pilot when the plane is parked at the transfer gate.
	 */

	public synchronized boolean parkAtTransferGate() {

		GenericIO.writelnString("Park at transfer gate");
		if (flightNum != 0) {
			repos.setPilotState(PilotStates.ATTRANSFERGATE);
		}
		if (lastFlight || remainingPassengers == 0) {
			repos.printFinal();
			return true;
		}
		return false;
	}

	/**
	 *  Operation inform plane ready for boarding.
	 *
	 *  It is called by the pilot when the plane is ready for boarding.
	 */

	public synchronized void informPlaneReadyForBoarding() {

		GenericIO.writelnString("Plane ready for flight!");
		readyForBoarding = true;
		flightNum++;
		if (remainingPassengers <= SimulPar.MIN) {
			lastFlight = true;
		}
		repos.print("\nFlight " + flightNum + ": boarding started.");
		repos.setPilotState(PilotStates.READYFORBOARDING);
	}

	/**
	 *  Operation wait for all in board.
	 *
	 *  It is called by the pilot when he is waiting for the passengers to board the plane.
	 */

	public synchronized void waitForAllInBoard() {
		repos.setPilotState(PilotStates.WAITINGFORBOARDING);

		notifyAll();

		while (!informPilot)
		{
        	try
	        {
        		wait ();
	        }
	        catch (InterruptedException e) {}
		}
		informPilot = false;
	}

	/**
	 *  Operation prepare for passing boarding.
	 *
	 *  It is called by the hostess to start the boarding process.
	 */

	public synchronized void prepareForPassBoarding() {

		// Hostess is waiting for the pilot info
		while (!readyForBoarding)
		{
        	try
	        {
        		GenericIO.writelnString("Waiting for boarding!");
        		wait ();
	        }
	        catch (InterruptedException e) {}
		}
		GenericIO.writelnString("Preparing for boarding!");
	}

	/**
	 *  Operation wait in queue.
	 *
	 *  It is called by each passenger when they go to wait in the queue.
	 */

	public synchronized void waitInQueue(int passengerId)
	{
      try
      {
    	  GenericIO.writelnString("Passenger " + passengerId + " added to queue");
    	  inQueue.write(passengerId);                    	// the passenger enters the Queue
    	  nQueue++;
    	  repos.addToQ();									// update the repository
    	  documentsShowed[passengerId] = false;
      }
      catch (MemException e)
      {
    	  GenericIO.writelnString ("Insertion of passenger id in queue failed: " + e.getMessage ());
          System.exit (1);
      }

      repos.setPassengerState (passengerId, PassengerStates.INQUEUE);

      notifyAll ();


      while (actualId != passengerId)
      {
        try
        {
        	wait ();
        }
        catch (InterruptedException e) {}
      }
	}

	/**
	 *  Operation wait for next passenger.
	 *
	 *  It is called by the hostess when she's ready to check the next passenger.
	 *
	 *  @return passenger id or -1 if the plane is ready to take off.
	 */

	public synchronized int waitForNextPassenger() {

		readyForBoarding = false;
		repos.setHostessState(HostessStates.WAITFORPASSENGER);

		if (passengersOnPlane == SimulPar.MAX) {
			readyToTakeOff = true;
			return -1;
		}

		if (remainingPassengers == 0 && lastFlight) {
			readyToTakeOff = true;
			return -1;
		}

		while(nQueue == 0) {
			try {
				if (passengersOnPlane >= SimulPar.MIN) {

					readyToTakeOff = true;
					return -1;
				}
				wait();
			} catch(Exception e) {
				return -1;
			}
		}
		nQueue -= 1;
		repos.removeFromQ();
		int passengerId = -1;
		try {
			passengerId = inQueue.read();
			actualId = passengerId;

		} catch (MemException e) {
			e.printStackTrace();
		}

		notifyAll();

		GenericIO.writelnString("Waiting for passenger " + passengerId);

		return passengerId;
	}

	/**
	 *  Operation show documents.
	 *
	 *  It is called by the passengers when they show the documents to the hostess.
	 */

	public synchronized void showDocuments(int passengerId) {

	    documentsShowed[passengerId] = true;
	    notifyAll();
	    while (stateID[passengerId] != PassengerStates.INFLIGHT) {
	    	try {
	    		wait();
	    	} catch(InterruptedException e) {}
	    }
	}

	/**
	 *  Operation check documents.
	 *
	 *  It is called by the hostess when she checks the passenger's documents,
	 *  and also makes sure it's the correct passenger.
	 *
	 * @param passengerID id of the passenger
	 */
	public synchronized void checkDocuments(int passengerID) {

		while (actualId != passengerID)
		{
			try
			{
				wait ();
			}
			catch (InterruptedException e) {}
		}

		notifyAll();

		repos.print("\nFlight " + flightNum + ": passenger " + passengerID + " checked.");

		repos.setHostessState(HostessStates.CHECKPASSENGER);
		remainingPassengers--;
		GenericIO.writelnString("Remaining passengers: " + remainingPassengers);
		GenericIO.writelnString("Checking passenger " + passengerID + " documents!");

		stateID[passengerID] = PassengerStates.INFLIGHT;

		repos.addToF();
		repos.setPassengerState (passengerID, PassengerStates.INFLIGHT);

		passengersOnPlane++;
		if (remainingPassengers == 0 && lastFlight) {
			readyToTakeOff = true;
		}
	}

	/**
	 *  Operation inform plane ready to take off.
	 *
	 *  It is called by the hostess when the plane has a valid number of passengers
	 *  and is ready to take off.
	 */

	public synchronized void informPlaneReadyToTakeOff() {

		System.out.println("Hospedeira a espera");
		try {
			Thread.currentThread ().sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (!readyToTakeOff)
		{
			try
			{
				wait ();
			}
			catch (InterruptedException e) {}
		}
		readyToTakeOff = false;
		GenericIO.writelnString("Plane ready to take off with " + passengersOnPlane + " passengers!!");
		
		repos.print("\nFlight " + flightNum + ": departed with " + passengersOnPlane + " passengers.");

		notifyAll();
		repos.setHostessState(HostessStates.READYTOFLY);

		informPilot = true;
	}

	/**
	 *  Operation wait for next flight.
	 *
	 *  It is called by the hostess when the plane has taken off and
	 *  she is now waiting for the next flight.
	 */

	public synchronized void waitForNextFlight() {

		passengersOnPlane = 0;
		repos.setHostessState(HostessStates.WAITFORFLIGHT);

		while(!readyForBoarding) {
			try {
				wait();
			} catch(Exception e) {}
		}
	}

	/**
	 *  Operation all passengers left.
	 *
	 *  It is called by the hostess and the pilot to check if there are still passengers to board.
	 *
	 *  @return true if all passengers have left the departing airport.
	 */

	public boolean allPassengLeft() {

		if (remainingPassengers == 0) {
			notifyAll();
			readyForBoarding = true;
			return true;
		}
		return false;
	}

	/**
     * Terminate the Departure airport service.
     */
	
	public void serviceEnd() {
		MainDepartureAirport.serviceEnd = true;
	}
}
