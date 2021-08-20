package clientSide.entities;

import interfaces.*;
import java.rmi.RemoteException;
import genclass.GenericIO;

/**
 *   Passenger thread.
 *
 *   It simulates the passenger life cycle.
 */

public class Passenger extends Thread
{
	/**
	 *  Passenger identification.
	 */

	private int passengerId;

	/**
	 *  Passenger state.
	 */

	private int passengerState;

	/**
	 *  Reference to the departure airport.
	 */

	private final DepAirportInt departAirportInt;

	/**
	 *  Reference to the plane.
	 */

	private final PlaneInt planeInt;

	/**
	 *   Instantiation of a Passenger thread.
	 *
	 *     @param name thread name
	 *     @param passengerId passenger id
	 *     @param departAirportInt reference to the Departure Airport
	 *     @param planeInt reference to the Plane
	 */

	public Passenger (String name, int passengerId, DepAirportInt departAirportInt, PlaneInt planeInt)
	{
		super (name);
		this.passengerId = passengerId;
		passengerState = PassengerStates.GOINGTOAIRPORT;
		this.departAirportInt = departAirportInt;
		this.planeInt = planeInt;
	}

	/**
	 *   Set passenger id.
	 *
	 *     @param id passenger id
	 */

	public void setPassengerId (int id)
	{
		passengerId = id;
	}

	/**
	 *   Get passenger id.
	 *
	 *     @return passenger id
	 */

	public int getPassengerId ()
	{
		return passengerId;
	}

	/**
	 *   Set passenger state.
	 *
	 *     @param state new passenger state
	 */

	public void setPassengerState (int state)
	{
		passengerState = state;
	}

	/**
	 *   Get passenger state.
	 *
	 *     @return passenger state
	 */

	public int getPassengerState ()
	{
		return passengerState;
	}

	/**
	 *   Life cycle of the passenger.
	 */

	@Override
	public void run ()
	{
		try {
			travelToAirport();
			departAirportInt.waitInQueue(getPassengerId());
			departAirportInt.showDocuments(getPassengerId());
			planeInt.boardThePlane(getPassengerId());
			planeInt.waitForEndOfFlight();
			planeInt.leaveThePlane();
		} catch (RemoteException ex) {
			GenericIO.writelnString ("Remote exception: " + ex.getMessage ());
			ex.printStackTrace ();
			System.exit (1);
		}
	}

	/**
	 *  Travel to Airport.
	 *
	 *  Internal operation.
	 */

	private void travelToAirport()
	{
		try
		{
			sleep ((long) (1 + 700 * Math.random ()));
		}
		catch (InterruptedException e) {}
		GenericIO.writelnString("Passenger " + passengerId + " is in the airport!");
	}
}
