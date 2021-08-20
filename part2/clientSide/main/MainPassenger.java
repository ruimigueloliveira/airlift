package clientSide.main;

import clientSide.entities.Passenger;
import clientSide.stubs.DepartureAirportStub;
import clientSide.stubs.PlaneStub;
import genclass.GenericIO;
import serverSide.main.SimulPar;

public class MainPassenger {

	public static void main(String[] args) {

		Passenger [] passenger = new Passenger [SimulPar.K];

		String depAirportServerHostName;                               // name of the platform where is located the departure airport server
		int depAirportServerPortNumb = -1;                             // port number for listening to service requests
		String planeServerHostName;                               	   // name of the platform where is located the plane server
		int planeServerPortNumb = -1;                             	   // port number for listening to service requests

		if (args.length != 4)
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

		DepartureAirportStub departAirport = new DepartureAirportStub(depAirportServerHostName, depAirportServerPortNumb);
		PlaneStub plane = new PlaneStub(planeServerHostName, planeServerPortNumb);

		for (int i = 0; i < SimulPar.K; i++)
			passenger[i] = new Passenger ("P" + i, i, departAirport, plane);

		for (int i = 0; i < SimulPar.K; i++)
			passenger[i].start ();

		for (int i = 0; i < SimulPar.K; i++)
		{ 
			try
			{ 
				passenger[i].join ();
			}
			catch (InterruptedException e) {}
			GenericIO.writelnString ("The passenger " + i + " has terminated.");
		}

	}

}
