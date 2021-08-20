echo "Compiling source code."
javac -cp ".:./genclass.jar:../.." */*.java */*/*.java


echo "Distributing intermediate code to the different execution environments."
echo "  RMI Registry"
mkdir -p dirRMIRegistry
mkdir -p dirRMIRegistry/interfaces
cp auxScriptsRemote/set_rmiregistry_d.sh dirRMIRegistry/
cp genclass.jar dirRMIRegistry/
cp interfaces/*.class dirRMIRegistry/interfaces

echo "  Register Remote Objects"
mkdir -p dirRegistry
mkdir -p dirRegistry/serverSide dirRegistry/serverSide/main dirRegistry/serverSide/objects dirRegistry/interfaces
cp serverSide/main/ServerRegisterRemoteObject.class dirRegistry/serverSide/main
cp serverSide/objects/RegisterRemoteObject.class dirRegistry/serverSide/objects
cp interfaces/Register.class dirRegistry/interfaces
cp auxScriptsRemote/registry_com_d.sh dirRegistry/
cp genclass.jar dirRegistry/
cp java.policy dirRegistry/

echo "  General Repository of Information"
mkdir -p dirGeneralRepos
mkdir -p dirGeneralRepos/serverSide dirGeneralRepos/serverSide/main dirGeneralRepos/serverSide/objects dirGeneralRepos/interfaces dirGeneralRepos/clientSide dirGeneralRepos/clientSide/entities
cp serverSide/main/SimulPar.class serverSide/main/MainRepos.class dirGeneralRepos/serverSide/main
cp serverSide/objects/GeneralRepos.class dirGeneralRepos/serverSide/objects
cp interfaces/Register.class interfaces/GeneralReposInt.class dirGeneralRepos/interfaces
cp clientSide/entities/HostessStates.class clientSide/entities/PassengerStates.class clientSide/entities/PilotStates.class dirGeneralRepos/clientSide/entities
cp auxScriptsRemote/general_repos_com_d.sh dirGeneralRepos/
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
cp auxScriptsRemote/departure_airport_com_d.sh dirDepartureAirport/
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
cp auxScriptsRemote/destination_airport_com_d.sh dirDestinationAirport/
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
cp auxScriptsRemote/plane_com_d.sh dirPlane/
cp genclass.jar dirPlane/
cp java.policy dirPlane/

echo "  Hostess"
mkdir -p dirHostess
mkdir -p dirHostess/serverSide dirHostess/serverSide/main dirHostess/clientSide dirHostess/clientSide/main dirHostess/clientSide/entities dirHostess/interfaces
cp serverSide/main/SimulPar.class dirHostess/serverSide/main
cp clientSide/main/MainHostess.class dirHostess/clientSide/main
cp clientSide/entities/Hostess.class clientSide/entities/HostessStates.class dirHostess/clientSide/entities
cp interfaces/GeneralReposInt.class interfaces/DepAirportInt.class dirHostess/interfaces
cp auxScriptsRemote/hostess_com_d.sh dirHostess/
cp genclass.jar dirHostess/
cp java.policy dirHostess/

echo "  Pilot"
mkdir -p dirPilot
mkdir -p dirPilot/serverSide dirPilot/serverSide/main dirPilot/clientSide dirPilot/clientSide/main dirPilot/clientSide/entities dirPilot/interfaces
cp serverSide/main/SimulPar.class dirPilot/serverSide/main
cp clientSide/main/MainPilot.class dirPilot/clientSide/main
cp clientSide/entities/Pilot.class clientSide/entities/PilotStates.class dirPilot/clientSide/entities
cp interfaces/GeneralReposInt.class interfaces/DepAirportInt.class interfaces/PlaneInt.class interfaces/DestAirportInt.class dirPilot/interfaces
cp auxScriptsRemote/pilot_com_d.sh dirPilot/
cp genclass.jar dirPilot/

echo "  Passenger"
mkdir -p dirPassenger
mkdir -p dirPassenger/serverSide dirPassenger/serverSide/main dirPassenger/clientSide dirPassenger/clientSide/main dirPassenger/clientSide/entities dirPassenger/interfaces
cp serverSide/main/SimulPar.class dirPassenger/serverSide/main
cp clientSide/main/MainPassenger.class dirPassenger/clientSide/main
cp clientSide/entities/Passenger.class clientSide/entities/PassengerStates.class dirPassenger/clientSide/entities
cp interfaces/GeneralReposInt.class interfaces/DepAirportInt.class interfaces/PlaneInt.class dirPassenger/interfaces
cp auxScriptsRemote/passenger_com_d.sh dirPassenger/
cp genclass.jar dirPassenger/

echo "Compressing execution environments."
echo "  RMI registry"
rm -f  dirRMIRegistry.zip
zip -rq dirRMIRegistry.zip dirRMIRegistry
echo "  Register Remote Objects"
rm -f  dirRegistry.zip
zip -rq dirRegistry.zip dirRegistry
echo "  General Repository of Information"
rm -f  dirGeneralRepos.zip
zip -rq dirGeneralRepos.zip dirGeneralRepos
echo "  Departure Airport"
rm -f  dirDepartureAirport.zip
zip -rq dirDepartureAirport.zip dirDepartureAirport
echo "  Destination Airport"
rm -f  dirDestinationAirport.zip
zip -rq dirDestinationAirport.zip dirDestinationAirport
echo "  Plane"
rm -f  dirPlane.zip
zip -rq dirPlane.zip dirPlane
echo "  Hostess"
rm -f  dirHostess.zip
zip -rq dirHostess.zip dirHostess
echo "  Pilot"
rm -f  dirPilot.zip
zip -rq dirPilot.zip dirPilot
echo "  Passenger"
rm -f  dirPassenger.zip
zip -rq dirPassenger.zip dirPassenger


printf "Cleaning ports."
# printf "\n  "
# sshpass -f password ssh sd308@l040101-ws01.ua.pt 'pkill -u sd308'
printf "\n  "
sshpass -f password ssh sd308@l040101-ws02.ua.pt 'pkill -u sd308'
printf "  "
sshpass -f password ssh sd308@l040101-ws03.ua.pt 'pkill -u sd308'
printf "  "
sshpass -f password ssh sd308@l040101-ws04.ua.pt 'pkill -u sd308'
printf "  "
sshpass -f password ssh sd308@l040101-ws05.ua.pt 'pkill -u sd308'
printf "  " 
sshpass -f password ssh sd308@l040101-ws06.ua.pt 'pkill -u sd308'
printf "  "
sshpass -f password ssh sd308@l040101-ws07.ua.pt 'pkill -u sd308'
printf "  "
sshpass -f password ssh sd308@l040101-ws08.ua.pt 'pkill -u sd308'
printf "  "
sshpass -f password ssh sd308@l040101-ws09.ua.pt 'pkill -u sd308'
printf "  "
sshpass -f password ssh sd308@l040101-ws10.ua.pt 'pkill -u sd308'
