package serverSide.main;

import clientSide.stubs.*;
import commInfra.ServerCom;
import genclass.GenericIO;
import serverSide.entities.*;
import serverSide.sharedRegions.*;

public class MainDepartureAirport {

	/**
	 * Used to check if the service must terminate.
	 */

	public static boolean serviceEnd = false;

	/**
	 * Main departure airport launcher
	 * @param args args
	 */

	public static void main(String[] args) {

		GeneralReposStub repos;                                        
		int portNumb = -1;                                             // port number for listening to service requests
		String reposServerName;                                        // name of the platform where is located the server for the general repository
		int reposPortNumb = -1;                                        // port nunber where the server for the general repository is listening to service requests

		if (args.length != 3)
		{
			GenericIO.writelnString ("Wrong number of parameters!");
			System.exit (1);
		}
		try
		{
			portNumb = Integer.parseInt (args[0]);
		}
		catch (NumberFormatException e)
		{
			GenericIO.writelnString ("args[0] is not a number!");
			System.exit (1);
		}
		if ((portNumb < 4000) || (portNumb >= 65536))
		{
			GenericIO.writelnString ("args[0] is not a valid port number!");
			System.exit (1);
		}
		reposServerName = args[1];
		try
		{
			reposPortNumb = Integer.parseInt (args[2]);
		}
		catch (NumberFormatException e)
		{
			GenericIO.writelnString ("args[2] is not a number!");
			System.exit (1);
		}
		if ((reposPortNumb < 4000) || (reposPortNumb >= 65536))
		{
			GenericIO.writelnString ("args[2] is not a valid port number!");
			System.exit (1);
		}

		/**
		 * Communication channels.
		 */

		ServerCom scon, sconi;
		ServiceProviderDeparture sp;

		/**
		 * Stub initialization.
		 */

		repos = new GeneralReposStub(reposServerName, reposPortNumb);

		/**
		 * Shared region and proxy initialization.
		 */

		DepartureAirport depAirport = new DepartureAirport(repos);
		DepartureAirportProxy depProxy = new DepartureAirportProxy(depAirport);

		/**
		 * Start listening on the communication channel.
		 */

		scon = new ServerCom(portNumb);
		scon.start();
		GenericIO.writelnString ("Service is established!");
		GenericIO.writelnString ("Server is listening for service requests.");

		/**
		 * While the service is not terminated, accept connections and send them
		 * to the service provider.
		 */

		while(!serviceEnd){
			sconi = scon.accept();
			sp = new ServiceProviderDeparture(sconi, depProxy);
			sp.start();
		}
		scon.end();
		GenericIO.writelnString ("Server was shutdown!");
	}
}
