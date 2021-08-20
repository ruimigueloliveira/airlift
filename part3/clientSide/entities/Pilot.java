package clientSide.entities;

import interfaces.*;
import java.rmi.RemoteException;
import genclass.GenericIO;

/**
 *   Pilot thread.
 *
 *   It simulates the pilot life cycle.
 */

public class Pilot extends Thread
{

	/**
	 *  Pilot state.
	 */

	private int pilotState;

	/**
	 *    Reference to the departure airport.
	 */

	private final DepAirportInt departAirportInt;

	/**
	 * 	Reference to the plane.
	 */

	private final PlaneInt planeInt;

	/**
	 * 	Reference to the destination airport.
	 */

	private final DestAirportInt destAirportInt;

	/**
	 * 	Instantiation of the hostess thread.
	 *
	 * 	@param name thread name
	 * 	@param departAirportInt reference to the Departure Airport
	 * 	@param planeInt reference to the plane
	 * 	@param destAirportInt reference to the Destination Airport
	 */

	public Pilot (String name, DepAirportInt departAirportInt, PlaneInt planeInt, DestAirportInt destAirportInt)
	{
		super (name);
		pilotState = PilotStates.ATTRANSFERGATE;
		this.departAirportInt = departAirportInt;
		this.planeInt = planeInt;
		this.destAirportInt = destAirportInt;
	}

	/**
	 *   Set pilot state.
	 *
	 *     @param state new pilot state
	 */

	public void setPilotState (int state)
	{
		pilotState = state;
	}

	/**
	 *   Get pilot state.
	 *
	 *     @return pilot state
	 */

	public int getPilotState ()
	{
		return pilotState;
	}

	/**
	 *   Life cycle of the pilot.
	 */

	@Override
	public void run() {
		try {
			while(true) {
				boolean last = departAirportInt.parkAtTransferGate();
				if (last) {				// Ends the service after the last flight
					break;
				}
				preparingForBoarding();
				departAirportInt.informPlaneReadyForBoarding();
				departAirportInt.waitForAllInBoard();
				planeInt.flyToDestinationPoint();
				flyingToDestination();
				planeInt.announceArrival();
				destAirportInt.flyToDeparturePoint();
				flyingBack();
			}

		} catch (RemoteException ex) {
			GenericIO.writelnString ("Remote exception: " + ex.getMessage ());
			ex.printStackTrace ();
			System.exit (1);
		}
	}


	/**
	 *  Flying to destination.
	 *
	 *  Internal operation.
	 */

	private void flyingToDestination()
	{
		try
		{ sleep ((long) (1 + 100 * Math.random ()));
		}
		catch (InterruptedException e) {}
	}

	/**
	 *  Flying back to departure.
	 *
	 *  Internal operation.
	 */

	private void flyingBack()
	{
		try
		{ sleep ((long) (1 + 100 * Math.random ()));
		}
		catch (InterruptedException e) {}
	}

	/**
	 *  Preparing for boarding.
	 *
	 *  Internal operation.
	 */

	private void preparingForBoarding()
	{
		try
		{ sleep ((long) (1 + 10 * Math.random ()));
		}
		catch (InterruptedException e) {}
	}
}
