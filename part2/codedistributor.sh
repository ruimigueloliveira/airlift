echo "Distributing intermediate code to the different execution environments."


echo "  General Repository"
rm -rf dirGeneralRepos

mkdir -p dirGeneralRepos \
dirGeneralRepos/serverSide \
dirGeneralRepos/serverSide/main \
dirGeneralRepos/serverSide/entities \
dirGeneralRepos/serverSide/sharedRegions \
dirGeneralRepos/clientSide \
dirGeneralRepos/clientSide/entities \
dirGeneralRepos/commInfra

cp serverSide/main/SimulPar.class serverSide/main/MainRepos.class serverSide/main/MainPlane.class \
 serverSide/main/MainDepartureAirport.class serverSide/main/MainDestinationAirport.class dirGeneralRepos/serverSide/main
cp serverSide/entities/*.class dirGeneralRepos/serverSide/entities
cp serverSide/sharedRegions/ServiceProviderRepos.class serverSide/sharedRegions/GeneralRepos.class serverSide/sharedRegions/IntGeneralRepos.class dirGeneralRepos/serverSide/sharedRegions

cp clientSide/entities/HostessStates.class clientSide/entities/PassengerStates.class clientSide/entities/PilotStates.class dirGeneralRepos/clientSide/entities

cp commInfra/*.class dirGeneralRepos/commInfra

cp genclass.jar dirGeneralRepos/


echo "  Departure Airport"
rm -rf dirDepartureAirport

mkdir -p dirDepartureAirport \
dirDepartureAirport/serverSide \
dirDepartureAirport/serverSide/main \
dirDepartureAirport/serverSide/entities \
dirDepartureAirport/serverSide/sharedRegions \
dirDepartureAirport/clientSide \
dirDepartureAirport/clientSide/entities \
dirDepartureAirport/clientSide/stubs \
dirDepartureAirport/commInfra

cp serverSide/main/SimulPar.class serverSide/main/MainRepos.class serverSide/main/MainPlane.class \
 serverSide/main/MainDepartureAirport.class serverSide/main/MainDestinationAirport.class dirDepartureAirport/serverSide/main
cp serverSide/entities/*.class dirDepartureAirport/serverSide/entities
cp serverSide/sharedRegions/*.class dirDepartureAirport/serverSide/sharedRegions

cp clientSide/entities/HostessStates.class clientSide/entities/PassengerStates.class clientSide/entities/PilotStates.class dirDepartureAirport/clientSide/entities
cp clientSide/stubs/GeneralReposStub.class dirDepartureAirport/clientSide/stubs

cp commInfra/*.class dirDepartureAirport/commInfra

cp genclass.jar dirDepartureAirport/


echo "  Destination Airport"
rm -rf dirDestinationAirport

mkdir -p dirDestinationAirport \
dirDestinationAirport/serverSide \
dirDestinationAirport/serverSide/main \
dirDestinationAirport/serverSide/entities \
dirDestinationAirport/serverSide/sharedRegions \
dirDestinationAirport/clientSide \
dirDestinationAirport/clientSide/entities \
dirDestinationAirport/clientSide/stubs \
dirDestinationAirport/commInfra

cp serverSide/main/SimulPar.class serverSide/main/MainRepos.class serverSide/main/MainPlane.class \
 serverSide/main/MainDepartureAirport.class serverSide/main/MainDestinationAirport.class dirDestinationAirport/serverSide/main
cp serverSide/entities/*.class dirDestinationAirport/serverSide/entities
cp serverSide/sharedRegions/ServiceProviderDestination.class serverSide/sharedRegions/DestinationAirport.class serverSide/sharedRegions/IntDestinationAirport.class dirDestinationAirport/serverSide/sharedRegions

cp clientSide/entities/HostessStates.class clientSide/entities/PassengerStates.class clientSide/entities/PilotStates.class dirDestinationAirport/clientSide/entities
cp clientSide/stubs/GeneralReposStub.class dirDestinationAirport/clientSide/stubs

cp commInfra/*.class dirDestinationAirport/commInfra

cp genclass.jar dirDestinationAirport/


echo "  Plane "
rm -rf dirPlane

mkdir -p dirPlane \
dirPlane/serverSide \
dirPlane/serverSide/main \
dirPlane/serverSide/entities \
dirPlane/serverSide/sharedRegions \
dirPlane/clientSide \
dirPlane/clientSide/entities \
dirPlane/clientSide/stubs \
dirPlane/commInfra

cp serverSide/main/SimulPar.class serverSide/main/MainRepos.class serverSide/main/MainPlane.class \
 serverSide/main/MainDepartureAirport.class serverSide/main/MainDestinationAirport.class dirPlane/serverSide/main
cp serverSide/entities/*.class dirPlane/serverSide/entities
cp serverSide/sharedRegions/ServiceProviderPlane.class serverSide/sharedRegions/Plane.class serverSide/sharedRegions/IntPlane.class dirPlane/serverSide/sharedRegions

cp clientSide/entities/HostessStates.class clientSide/entities/PassengerStates.class clientSide/entities/PilotStates.class dirPlane/clientSide/entities
cp clientSide/stubs/GeneralReposStub.class dirPlane/clientSide/stubs

cp commInfra/*.class dirPlane/commInfra

cp genclass.jar dirPlane/


echo "  Hostess"
rm -rf dirHostess

mkdir -p dirHostess \
dirHostess/serverSide \
dirHostess/serverSide/main \
dirHostess/clientSide \
dirHostess/clientSide/main \
dirHostess/clientSide/entities \
dirHostess/clientSide/stubs \
dirHostess/commInfra

cp serverSide/main/SimulPar.class dirHostess/serverSide/main

cp clientSide/main/MainHostess.class dirHostess/clientSide/main
cp clientSide/entities/Hostess.class clientSide/entities/HostessStates.class dirHostess/clientSide/entities
cp clientSide/stubs/GeneralReposStub.class clientSide/stubs/DepartureAirportStub.class dirHostess/clientSide/stubs

cp commInfra/*.class dirHostess/commInfra

cp genclass.jar dirHostess/


echo "  Pilot"
rm -rf dirPilot

mkdir -p dirPilot \
dirPilot/serverSide \
dirPilot/serverSide/main \
dirPilot/clientSide \
dirPilot/clientSide/main \
dirPilot/clientSide/entities \
dirPilot/clientSide/stubs \
dirPilot/commInfra

cp serverSide/main/SimulPar.class dirPilot/serverSide/main

cp clientSide/main/MainPilot.class dirPilot/clientSide/main
cp clientSide/entities/Pilot.class clientSide/entities/PilotStates.class dirPilot/clientSide/entities
cp clientSide/stubs/GeneralReposStub.class clientSide/stubs/DepartureAirportStub.class clientSide/stubs/DestinationAirportStub.class clientSide/stubs/PlaneStub.class dirPilot/clientSide/stubs

cp commInfra/*.class dirPilot/commInfra

cp genclass.jar dirPilot/


echo "  Passenger"
rm -rf dirPassenger

mkdir -p dirPassenger \
dirPassenger/serverSide \
dirPassenger/serverSide/main \
dirPassenger/clientSide \
dirPassenger/clientSide/main \
dirPassenger/clientSide/entities \
dirPassenger/clientSide/stubs \
dirPassenger/commInfra

cp serverSide/main/SimulPar.class dirPassenger/serverSide/main

cp clientSide/main/MainPassenger.class dirPassenger/clientSide/main
cp clientSide/entities/Passenger.class clientSide/entities/PassengerStates.class dirPassenger/clientSide/entities
cp clientSide/stubs/GeneralReposStub.class clientSide/stubs/DepartureAirportStub.class clientSide/stubs/PlaneStub.class dirPassenger/clientSide/stubs

cp commInfra/*.class dirPassenger/commInfra

cp genclass.jar dirPassenger/


echo "Compressing execution environments."

echo "  General Repository"
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


echo "Deploying and decompressing execution environments."
mkdir -p newGeneratedFolder
rm -rf newGeneratedFolder/*

mv dirGeneralRepos.zip newGeneratedFolder/
mv dirDepartureAirport.zip newGeneratedFolder/
mv dirDestinationAirport.zip newGeneratedFolder/
mv dirPlane.zip newGeneratedFolder/

mv dirHostess.zip newGeneratedFolder/
mv dirPilot.zip newGeneratedFolder/
mv dirPassenger.zip newGeneratedFolder/

cd newGeneratedFolder/
unzip -q dirGeneralRepos.zip
unzip -q dirDepartureAirport.zip
unzip -q dirDestinationAirport.zip
unzip -q dirPlane.zip

unzip -q dirHostess.zip
unzip -q dirPilot.zip
unzip -q dirPassenger.zip

cd ..

rm -rf dirGeneralRepos
rm -rf dirDepartureAirport
rm -rf dirDestinationAirport
rm -rf dirPlane

rm -rf dirHostess
rm -rf dirPilot
rm -rf dirPassenger
