package clientSide.entities;

import clientSide.stubs.DepartureAirportStub;
import clientSide.stubs.PlaneStub;
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

	private final DepartureAirportStub departAirport;

	/**
	 *  Reference to the plane.
	 */

	private final PlaneStub plane;

	/**
	 *   Instantiation of a Passenger thread.
	 *
	 *     @param name thread name
	 *     @param passengerId passenger id
	 *     @param departAirport reference to the Departure Airport
	 *     @param plane reference to the Plane
	 */

	public Passenger (String name, int passengerId, DepartureAirportStub departAirport, PlaneStub plane)
	{
		super (name);
		this.passengerId = passengerId;
		passengerState = PassengerStates.GOINGTOAIRPORT;
		this.departAirport = departAirport;
		this.plane = plane;
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
		travelToAirport();
		departAirport.waitInQueue(getPassengerId());
		departAirport.showDocuments(getPassengerId());
		plane.boardThePlane(getPassengerId());
		plane.waitForEndOfFlight(getPassengerId());
		plane.leaveThePlane(getPassengerId());
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
