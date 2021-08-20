echo "Transfering data to the departure airport node."
sshpass -f password ssh sd308@l040101-ws02.ua.pt 'mkdir -p test/AirLIft'
sshpass -f password ssh sd308@l040101-ws02.ua.pt 'rm -rf test/AirLIft/*'
sshpass -f password scp newGeneratedFolder/dirDepartureAirport.zip sd308@l040101-ws02.ua.pt:test/AirLIft
echo "Decompressing data sent to the departure airport node."
sshpass -f password ssh sd308@l040101-ws02.ua.pt 'cd test/AirLIft ; unzip -uq dirDepartureAirport.zip'
echo "Executing program at the departure airport node."
sshpass -f password ssh sd308@l040101-ws02.ua.pt 'cd test/AirLIft/dirDepartureAirport ; java -cp ".:./genclass.jar" serverSide.main.MainDepartureAirport 22002 l040101-ws01.ua.pt 22001'