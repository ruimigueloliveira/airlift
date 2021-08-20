cd ..
echo "Transfering data to the Departure Airport node."
sshpass -f password ssh sd308@l040101-ws07.ua.pt 'mkdir -p test/AirLift-RMI'
sshpass -f password ssh sd308@l040101-ws07.ua.pt 'rm -rf test/AirLift-RMI/*'
sshpass -f password scp dirDepartureAirport.zip sd308@l040101-ws07.ua.pt:test/AirLift-RMI
echo "Decompressing data sent to the Departure Airport node."
sshpass -f password ssh sd308@l040101-ws07.ua.pt 'cd test/AirLift-RMI ; unzip -uq dirDepartureAirport.zip'
echo "Executing program at the Departure Airport node."
sshpass -f password ssh sd308@l040101-ws07.ua.pt 'cd test/AirLift-RMI/dirDepartureAirport ; ./departure_airport_com_d.sh sd308'
