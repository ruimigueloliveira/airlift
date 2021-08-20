cd ..
echo "Transfering data to the Destination Airport node."
sshpass -f password ssh sd308@l040101-ws06.ua.pt 'mkdir -p test/AirLift-RMI'
sshpass -f password ssh sd308@l040101-ws06.ua.pt 'rm -rf test/AirLift-RMI/*'
sshpass -f password scp dirDestinationAirport.zip sd308@l040101-ws06.ua.pt:test/AirLift-RMI
echo "Decompressing data sent to the Destination Airport node."
sshpass -f password ssh sd308@l040101-ws06.ua.pt 'cd test/AirLift-RMI ; unzip -uq dirDestinationAirport.zip'
echo "Executing program at the Destination Airport node."
sshpass -f password ssh sd308@l040101-ws06.ua.pt 'cd test/AirLift-RMI/dirDestinationAirport ; ./destination_airport_com_d.sh sd308'
