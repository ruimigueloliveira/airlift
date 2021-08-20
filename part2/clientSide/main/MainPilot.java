package clientSide.main;

import clientSide.entities.Pilot;
import clientSide.stubs.*;
import genclass.GenericIO;

public class MainPilot {

	public static void main(String[] args) {

		String depAirportServerHostName;                               // name of the platform where is located the departure airport server
		int depAirportServerPortNumb = -1;                             // port number for listening to service requests
		String planeServerHostName;                               	   // name of the platform where is located the plane server
		int planeServerPortNumb = -1;                             	   // port number for listening to service requests
		String destAirportServerHostName;                              // name of the platform where is located the destination airport server
		int destAirportServerPortNumb = -1;                            // port number for listening to service requests

		if (args.length != 6)
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
		planeServerHostName = args[2];
		try
		{ 
			planeServerPortNumb = Integer.parseInt (args[3]);
		}
		catch (NumberFormatException e)
		{ 
			GenericIO.writelnString ("args[3] is not a number!");
			System.exit (1);
		}
		destAirportServerHostName = args[4];
		try
		{ 
			destAirportServerPortNumb = Integer.parseInt (args[5]);
		}
		catch (NumberFormatException e)
		{ 
			GenericIO.writelnString ("args[5] is not a number!");
			System.exit (1);
		}

		DepartureAirportStub departAirport = new DepartureAirportStub(depAirportServerHostName, depAirportServerPortNumb);
		PlaneStub plane = new PlaneStub(planeServerHostName, planeServerPortNumb);
		DestinationAirportStub destAirport = new DestinationAirportStub(destAirportServerHostName, destAirportServerPortNumb);


		Pilot pilot = new Pilot("PT", departAirport, plane, destAirport);
		pilot.start();

		try {
			pilot.join();
		} catch(InterruptedException e) {}
		GenericIO.writelnString ("The pilot has terminated.");

	}

}
