package clientSide.main;

import java.rmi.registry.*;
import java.rmi.*;
import clientSide.entities.*;
import interfaces.*;
import genclass.GenericIO;

/**
 *    Client side of the Air-Lift (hostess).
 *
 *    Implementation of a client-server model of type 2 (server replication).
 *    Communication is based on Java RMI.
 */

public class MainHostess {

	/**
	 *  Main method.
	 *
	 *    @param args runtime arguments
	 *        args[0] - name of the platform where is located the RMI registering service
	 *        args[1] - port number where the registering service is listening to service requests
	 */

	public static void main(String[] args) {

		String rmiRegHostName;                                         // name of the platform where is located the RMI registering service
		int rmiRegPortNumb = -1;                                       // port number where the registering service is listening to service requests


		/* getting problem runtime parameters */

		if (args.length != 2)
		{ GenericIO.writelnString ("Wrong number of parameters!");
		System.exit (1);
		}
		rmiRegHostName = args[0];
		try
		{ rmiRegPortNumb = Integer.parseInt (args[1]);
		}
		catch (NumberFormatException e)
		{ GenericIO.writelnString ("args[1] is not a number!");
		System.exit (1);
		}
		if ((rmiRegPortNumb < 4000) || (rmiRegPortNumb >= 65536))
		{ GenericIO.writelnString ("args[1] is not a valid port number!");
		System.exit (1);
		}

		/* problem initialization */

		String nameEntryGeneralRepos = "GeneralRepository";            // public name of the general repository object
		GeneralReposInt reposStub = null;                        	   // remote reference to the general repository object
		String nameEntryDepAirport = "DepartureAirport";               // public name of the departure airport object
		DepAirportInt depAirportInt = null;                            // remote reference to the departure airport object
		Registry registry = null;                                      // remote reference for registration in the RMI registry service

		try
		{ registry = LocateRegistry.getRegistry (rmiRegHostName, rmiRegPortNumb);
		}
		catch (RemoteException e)
		{ GenericIO.writelnString ("RMI registry creation exception: " + e.getMessage ());
		e.printStackTrace ();
		System.exit (1);
		}

		try
		{ reposStub = (GeneralReposInt) registry.lookup (nameEntryGeneralRepos);
		}
		catch (RemoteException e)
		{ GenericIO.writelnString ("GeneralRepos lookup exception: " + e.getMessage ());
		e.printStackTrace ();
		System.exit (1);
		}
		catch (NotBoundException e)
		{ GenericIO.writelnString ("GeneralRepos not bound exception: " + e.getMessage ());
		e.printStackTrace ();
		System.exit (1);
		}

		try
		{ depAirportInt = (DepAirportInt) registry.lookup (nameEntryDepAirport);
		}
		catch (RemoteException e)
		{ GenericIO.writelnString ("Departure Airport lookup exception: " + e.getMessage ());
		e.printStackTrace ();
		System.exit (1);
		}
		catch (NotBoundException e)
		{ GenericIO.writelnString ("Departure Airport not bound exception: " + e.getMessage ());
		e.printStackTrace ();
		System.exit (1);
		}
		Hostess hostess = new Hostess("HT", depAirportInt);

		/* start of the simulation */

		hostess.start();

		/* waiting for the end of the simulation */

		try {
			hostess.join();
		} catch(InterruptedException e) {}
		GenericIO.writelnString ("The hostess has terminated.");

		try
		{ depAirportInt.shutdown();
		}
		catch (RemoteException e)
		{ GenericIO.writelnString ("Hostess generator remote exception on Departure Airport shutdown: " + e.getMessage ());
		System.exit (1);
		}
	}
}
