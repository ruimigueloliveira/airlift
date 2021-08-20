cd dirRMIRegistry
xterm  -T "RMI registry" -hold -e "./set_rmiregistry_alt.sh" &
sleep 4
cd ..

cd dirRegistry
xterm  -T "Registry" -hold -e "./registry_com_alt.sh" &
sleep 4
cd ..

cd dirGeneralRepos
xterm  -T "General Repository" -hold -e "./repos_com_alt.sh" &
sleep 4
cd ..

cd dirDepartureAirport
xterm  -T "Departure Airport" -hold -e "./departure_airport_com_alt.sh" &
sleep 4
cd ..

cd dirDestinationAirport
xterm  -T "Destination Airport" -hold -e "./destination_airport_com_alt.sh" &
sleep 4
cd ..

cd dirPlane
xterm  -T "Plane" -hold -e "./plane_com_alt.sh" &
sleep 4
cd ..

cd dirPilot
xterm  -T "Pilot" -hold -e "./pilot_com_alt.sh" &
sleep 4
cd ..

cd dirHostess
xterm  -T "Hostess" -hold -e "./hostess_com_alt.sh" &
sleep 4
cd ..

cd dirPassenger
xterm  -T "Passenger" -hold -e "./passenger_com_alt.sh" &
sleep 4
cd ..
