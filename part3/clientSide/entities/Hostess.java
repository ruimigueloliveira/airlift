package clientSide.entities;

import interfaces.*;
import java.rmi.RemoteException;
import genclass.GenericIO;

/**
 *   Hostess thread.
 *
 *   It simulates the hostess life cycle.
 */

public class Hostess extends Thread {

	/**
	 *  Hostess state.
	 */

	private int hostessState;

	/**
	 *  Reference to the departure airport.
	 */

	private final DepAirportInt departAirportInt;

	/**
	 *   Instantiation of a hostess thread.
	 *
	 *     @param name thread name
	 *     @param departAirportInt reference to the Departure Airport
	 */

	public Hostess (String name, DepAirportInt departAirportInt)
	{
		super (name);
		hostessState = HostessStates.WAITFORFLIGHT;
		this.departAirportInt = departAirportInt;
	}

	/**
	 *   Set hostess state.
	 *
	 *     @param state new hostess state
	 */

	public void setHostessState (int state)
	{
		hostessState = state;
	}

	/**
	 *   Get hostess state.
	 *
	 *     @return hostess state
	 */

	public int getHostessState ()
	{
		return hostessState;
	}

	/**
	 *   Life cycle of the hostess.
	 */

	@Override
	public void run() {

		try {
			while(true) {
				departAirportInt.prepareForPassBoarding();

				while (true) {
					int id = departAirportInt.waitForNextPassenger();
					if (id == -1) break;
					departAirportInt.checkDocuments(id);
				}
				departAirportInt.informPlaneReadyToTakeOff();
				departAirportInt.waitForNextFlight();
				if (departAirportInt.allPassengLeft()) {
					break;
				}
			}
		} catch (RemoteException ex) {
			GenericIO.writelnString ("Remote exception: " + ex.getMessage ());
			ex.printStackTrace ();
			System.exit (1);
		}
	}
}
