cd auxScriptsRemote
xterm  -T "RMI registry" -hold -e "./RMIRegistryDeployAndRun.sh" &
sleep 4

xterm  -T "Registry" -hold -e "./RegistryDeployAndRun.sh" &
sleep 4

xterm  -T "General Repository" -hold -e "./GeneralReposDeployAndRun.sh" &
sleep 4

xterm  -T "Departure Airport" -hold -e "./DepartureAirportDeployAndRun.sh" &
sleep 4

xterm  -T "Destination Airport" -hold -e "./DestinationAirportDeployAndRun.sh" &
sleep 4

xterm  -T "Plane" -hold -e "./PlaneDeployAndRun.sh" &
sleep 4

xterm  -T "Pilot" -hold -e "./PilotDeployAndRun.sh" &
sleep 4

xterm  -T "Hostess" -hold -e "./HostessDeployAndRun.sh" &
sleep 4

xterm  -T "Passenger" -hold -e "./PassengerDeployAndRun.sh" &

