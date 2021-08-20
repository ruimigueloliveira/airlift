sudo kill -9 $(ps -ef | grep java | grep -v "netbeans" | awk '{ print $2 }')

echo -e "\n${bold}* Execução do código em cada nó *${normal}"

echo -e "\n${bold}->${normal} A executar repos"
cd newGeneratedFolder/dirGeneralRepos
xterm -T "General Repository" -hold -e java -cp ".:./genclass.jar:../.." serverSide/main/MainRepos 8081 &
cd ../..

echo -e "\n${bold}->${normal} A executar departure"
cd newGeneratedFolder/dirDepartureAirport
xterm -T "Departure Airport" -hold -e java -cp ".:./genclass.jar:../.." serverSide/main/MainDepartureAirport 8082 localhost 8081 &
cd ../..

echo -e "\n${bold}->${normal} A executar destination"
cd newGeneratedFolder/dirDestinationAirport
xterm -T "Destination Airport" -hold -e java -cp ".:./genclass.jar:../.." serverSide/main/MainDestinationAirport 8083 localhost 8081 &
cd ../..

echo -e "\n${bold}->${normal} A executar plane"
cd newGeneratedFolder/dirPlane
xterm -T "Plane" -hold -e java -cp ".:./genclass.jar:../.." serverSide/main/MainPlane 8084 localhost 8081 &
cd ../..

# Wait for the shared regions to be launched before launching the intervening enities

sleep 1

echo -e "\n${bold}->${normal} A executar Hostess"
cd newGeneratedFolder/dirHostess
xterm -T "Hostess" -hold -e java -cp ".:./genclass.jar" clientSide/main/MainHostess localhost 8082 &
cd ../..

echo -e "\n${bold}->${normal} A executar pilot"
cd newGeneratedFolder/dirPilot
xterm -T "Pilot" -hold -e java -cp ".:./genclass.jar" clientSide/main/MainPilot localhost 8082 localhost 8084 localhost 8083 &
cd ../..

echo -e "\n${bold}->${normal} A executar passenger"
cd newGeneratedFolder/dirPassenger
xterm -T "Passenger" -hold -e java -cp ".:./genclass.jar" clientSide/main/MainPassenger localhost 8082 localhost 8084 &
cd ../..

wait

sudo kill -9 $(ps -ef | grep java | grep -v "netbeans" | awk '{ print $2 }')

echo -e "\n${bold}->${normal} A execução terminou"
