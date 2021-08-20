echo "Compiling source code."
javac -cp ".:./genclass.jar:../.." */*.java */*/*.java


echo "Distributing intermediate code to the different execution environments."
echo "  RMI Registry"
mkdir -p dirRMIRegistry
mkdir -p dirRMIRegistry/interfaces
cp auxScriptsLocal/set_rmiregistry_alt.sh dirRMIRegistry/
cp genclass.jar dirRMIRegistry/
cp interfaces/*.class dirRMIRegistry/interfaces

echo "  Register Remote Objects"
mkdir -p dirRegistry
mkdir -p dirRegistry/serverSide dirRegistry/serverSide/main dirRegistry/serverSide/objects dirRegistry/interfaces
cp serverSide/main/ServerRegisterRemoteObject.class dirRegistry/serverSide/main
cp serverSide/objects/RegisterRemoteObject.class dirRegistry/serverSide/objects
cp interfaces/Register.class dirRegistry/interfaces
cp auxScriptsLocal/registry_com_alt.sh dirRegistry/
cp genclass.jar dirRegistry/
cp java.policy dirRegistry/

echo "  General Repository of Information"
mkdir -p dirGeneralRepos
mkdir -p dirGeneralRepos/serverSide dirGeneralRepos/serverSide/main dirGeneralRepos/serverSide/objects dirGeneralRepos/interfaces dirGeneralRepos/clientSide dirGeneralRepos/clientSide/entities
cp serverSide/main/SimulPar.class serverSide/main/MainRepos.class dirGeneralRepos/serverSide/main
cp serverSide/objects/GeneralRepos.class dirGeneralRepos/serverSide/objects
cp interfaces/Register.class interfaces/GeneralReposInt.class dirGeneralRepos/interfaces
cp clientSide/entities/HostessStates.class clientSide/entities/PassengerStates.class clientSide/entities/PilotStates.class dirGeneralRepos/clientSide/entities
cp auxScriptsLocal/repos_com_alt.sh dirGeneralRepos/
cp genclass.jar dirGeneralRepos/
cp java.policy dirGeneralRepos/

echo "  Departure Airport"
mkdir -p dirDepartureAirport
mkdir -p dirDepartureAirport/serverSide dirDepartureAirport/serverSide/main dirDepartureAirport/serverSide/objects dirDepartureAirport/interfaces dirDepartureAirport/clientSide dirDepartureAirport/clientSide/entities dirDepartureAirport/commInfra
cp serverSide/main/SimulPar.class serverSide/main/MainDepartureAirport.class dirDepartureAirport/serverSide/main
cp serverSide/objects/DepartureAirport.class dirDepartureAirport/serverSide/objects
cp interfaces/*.class dirDepartureAirport/interfaces
cp clientSide/entities/HostessStates.class clientSide/entities/PassengerStates.class clientSide/entities/PilotStates.class dirDepartureAirport/clientSide/entities
cp commInfra/*.class dirDepartureAirport/commInfra
cp auxScriptsLocal/departure_airport_com_alt.sh dirDepartureAirport/
cp genclass.jar dirDepartureAirport/
cp java.policy dirDepartureAirport/

echo "  Destination Airport"
mkdir -p dirDestinationAirport
mkdir -p dirDestinationAirport/serverSide dirDestinationAirport/serverSide/main dirDestinationAirport/serverSide/objects dirDestinationAirport/interfaces dirDestinationAirport/clientSide dirDestinationAirport/clientSide/entities dirDestinationAirport/commInfra
cp serverSide/main/SimulPar.class serverSide/main/MainDestinationAirport.class dirDestinationAirport/serverSide/main
cp serverSide/objects/DestinationAirport.class dirDestinationAirport/serverSide/objects
cp interfaces/*.class dirDestinationAirport/interfaces
cp clientSide/entities/PilotStates.class dirDestinationAirport/clientSide/entities
cp commInfra/*.class dirDestinationAirport/commInfra
cp auxScriptsLocal/destination_airport_com_alt.sh dirDestinationAirport/
cp genclass.jar dirDestinationAirport/
cp java.policy dirDestinationAirport/

echo "  Plane"
mkdir -p dirPlane
mkdir -p dirPlane/serverSide dirPlane/serverSide/main dirPlane/serverSide/objects dirPlane/interfaces dirPlane/clientSide dirPlane/clientSide/entities dirPlane/commInfra
cp serverSide/main/SimulPar.class serverSide/main/MainPlane.class dirPlane/serverSide/main
cp serverSide/objects/Plane.class dirPlane/serverSide/objects
cp interfaces/*.class dirPlane/interfaces
cp clientSide/entities/PassengerStates.class clientSide/entities/PilotStates.class dirPlane/clientSide/entities
cp commInfra/*.class dirPlane/commInfra
cp auxScriptsLocal/plane_com_alt.sh dirPlane/
cp genclass.jar dirPlane/
cp java.policy dirPlane/

echo "  Hostess"
mkdir -p dirHostess
mkdir -p dirHostess/serverSide dirHostess/serverSide/main dirHostess/clientSide dirHostess/clientSide/main dirHostess/clientSide/entities dirHostess/interfaces
cp serverSide/main/SimulPar.class dirHostess/serverSide/main
cp clientSide/main/MainHostess.class dirHostess/clientSide/main
cp clientSide/entities/Hostess.class clientSide/entities/HostessStates.class dirHostess/clientSide/entities
cp interfaces/GeneralReposInt.class interfaces/DepAirportInt.class dirHostess/interfaces
cp auxScriptsLocal/hostess_com_alt.sh dirHostess/
cp genclass.jar dirHostess/
cp java.policy dirHostess/

echo "  Pilot"
mkdir -p dirPilot
mkdir -p dirPilot/serverSide dirPilot/serverSide/main dirPilot/clientSide dirPilot/clientSide/main dirPilot/clientSide/entities dirPilot/interfaces
cp serverSide/main/SimulPar.class dirPilot/serverSide/main
cp clientSide/main/MainPilot.class dirPilot/clientSide/main
cp clientSide/entities/Pilot.class clientSide/entities/PilotStates.class dirPilot/clientSide/entities
cp interfaces/GeneralReposInt.class interfaces/DepAirportInt.class interfaces/PlaneInt.class interfaces/DestAirportInt.class dirPilot/interfaces
cp auxScriptsLocal/pilot_com_alt.sh dirPilot/
cp genclass.jar dirPilot/

echo "  Passenger"
mkdir -p dirPassenger
mkdir -p dirPassenger/serverSide dirPassenger/serverSide/main dirPassenger/clientSide dirPassenger/clientSide/main dirPassenger/clientSide/entities dirPassenger/interfaces
cp serverSide/main/SimulPar.class dirPassenger/serverSide/main
cp clientSide/main/MainPassenger.class dirPassenger/clientSide/main
cp clientSide/entities/Passenger.class clientSide/entities/PassengerStates.class dirPassenger/clientSide/entities
cp interfaces/GeneralReposInt.class interfaces/DepAirportInt.class interfaces/PlaneInt.class dirPassenger/interfaces
cp auxScriptsLocal/passenger_com_alt.sh dirPassenger/
cp genclass.jar dirPassenger/
