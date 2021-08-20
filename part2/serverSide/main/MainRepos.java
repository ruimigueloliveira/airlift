package serverSide.main;

import commInfra.ServerCom;
import genclass.GenericIO;
import serverSide.entities.GeneralReposProxy;
import serverSide.sharedRegions.GeneralRepos;
import serverSide.sharedRegions.ServiceProviderRepos;

public class MainRepos {

	/**
	 * Used to check if the service must terminate.
	 */

	public static boolean serviceEnd = false;

	/**
	 * Main general repository launcher
	 * @param args args
	 */

	public static void main(String[] args) {

		GeneralRepos repos;							// general repos (service to be rendered)
		int portNumb = -1;                          // port number for listening to service requests

		if (args.length != 1)
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

		/**
		 * Communication channels.
		 */

		ServerCom scon, sconi;
		ServiceProviderRepos sp;

		/**
		 * Parameter initialization.
		 */

		repos = new GeneralRepos("logger.txt");
		repos.reportInitialStatus();

		GeneralReposProxy reposProxy = new GeneralReposProxy(repos);

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

		while(!serviceEnd) {
			sconi = scon.accept();
			sp = new ServiceProviderRepos(sconi, reposProxy);
			sp.start();
		} 
		scon.end();
		GenericIO.writelnString ("Server was shutdown!");
	}
}
