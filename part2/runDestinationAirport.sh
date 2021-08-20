echo "Transfering data to the Destination airport node."
sshpass -f password ssh sd308@l040101-ws03.ua.pt 'mkdir -p test/AirLIft'
sshpass -f password ssh sd308@l040101-ws03.ua.pt 'rm -rf test/AirLIft/*'
sshpass -f password scp newGeneratedFolder/dirDestinationAirport.zip sd308@l040101-ws03.ua.pt:test/AirLIft
echo "Decompressing data sent to the Destination airport node."
sshpass -f password ssh sd308@l040101-ws03.ua.pt 'cd test/AirLIft ; unzip -uq dirDestinationAirport.zip'
echo "Executing program at the Destination airport node."
sshpass -f password ssh sd308@l040101-ws03.ua.pt 'cd test/AirLIft/dirDestinationAirport ; java -cp ".:./genclass.jar" serverSide.main.MainDestinationAirport 22003 l040101-ws01.ua.pt 22001'