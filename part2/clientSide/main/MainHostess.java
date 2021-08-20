package clientSide.main;

import clientSide.entities.Hostess;
import clientSide.stubs.DepartureAirportStub;
import genclass.GenericIO;

public class MainHostess {

	public static void main(String[] args) {

		String depAirportServerHostName;                               // name of the platform where is located the departure airport server
		int depAirportServerPortNumb = -1;                             // port number for listening to service requests

		if (args.length != 2)
		{ 
			GenericIO.writelnString ("Wrong number of parameters!");
			System.exit (1);
		}
		depAirportServerHostName = args[0];
		try
		{ 
			depAirportServerPortNumb = Integer.parseInt (args[1]);
		}
		catch (NumberFormatException e)
		{ 
			GenericIO.writelnString ("args[1] is not a number!");
			System.exit (1);
		}

		DepartureAirportStub departAirport = new DepartureAirportStub(depAirportServerHostName, depAirportServerPortNumb);
		Hostess hostess = new Hostess("HT", departAirport);
		hostess.start();

		try {
			hostess.join();
		} catch(InterruptedException e) {}
		GenericIO.writelnString ("The hostess has terminated.");

	}

}
