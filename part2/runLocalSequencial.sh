sudo kill -9 $(ps -ef | grep java | grep -v "netbeans" | awk '{ print $2 }')

echo -e "\n${bold}* Execução do código em cada nó *${normal}"

echo -e "\n${bold}->${normal} A executar repos"
cd newGeneratedFolder/dirGeneralRepos
java -cp ".:./genclass.jar:../.." serverSide/main/MainRepos 8081 &
cd ../..

echo -e "\n${bold}->${normal} A executar departure"
cd newGeneratedFolder/dirDepartureAirport
java -cp ".:./genclass.jar:../.." serverSide/main/MainDepartureAirport 8082 localhost 8081 &
cd ../..

echo -e "\n${bold}->${normal} A executar destination"
cd newGeneratedFolder/dirDestinationAirport
java -cp ".:./genclass.jar:../.." serverSide/main/MainDestinationAirport 8083 localhost 8081 &
cd ../..

echo -e "\n${bold}->${normal} A executar plane"
cd newGeneratedFolder/dirPlane
java -cp ".:./genclass.jar:../.." serverSide/main/MainPlane 8084 localhost 8081 &
cd ../..

# Wait for the shared regions to be launched before launching the intervening enities

sleep 1

echo -e "\n${bold}->${normal} A executar Hostess"
cd newGeneratedFolder/dirHostess
java -cp ".:./genclass.jar" clientSide/main/MainHostess localhost 8082 &
cd ../..

echo -e "\n${bold}->${normal} A executar pilot"
cd newGeneratedFolder/dirPilot
java -cp ".:./genclass.jar" clientSide/main/MainPilot localhost 8082 localhost 8084 localhost 8083 &
cd ../..

echo -e "\n${bold}->${normal} A executar passenger"
cd newGeneratedFolder/dirPassenger
java -cp ".:./genclass.jar" clientSide/main/MainPassenger localhost 8082 localhost 8084 &
cd ../..

wait

sudo kill -9 $(ps -ef | grep java | grep -v "netbeans" | awk '{ print $2 }')

echo -e "\n${bold}->${normal} A execução terminou"
