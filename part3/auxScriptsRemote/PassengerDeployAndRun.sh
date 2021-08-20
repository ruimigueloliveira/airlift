cd ..
echo "Transfering data to the Passenger node."
sshpass -f password ssh sd308@l040101-ws02.ua.pt 'mkdir -p test/AirLift-RMI'
sshpass -f password ssh sd308@l040101-ws02.ua.pt 'rm -rf test/AirLift-RMI/*'
sshpass -f password scp dirPassenger.zip sd308@l040101-ws02.ua.pt:test/AirLift-RMI
echo "Decompressing data sent to the Passenger node."
sshpass -f password ssh sd308@l040101-ws02.ua.pt 'cd test/AirLift-RMI ; unzip -uq dirPassenger.zip'
echo "Executing program at the Passenger node."
sshpass -f password ssh sd308@l040101-ws02.ua.pt 'cd test/AirLift-RMI/dirPassenger ; ./passenger_com_d.sh'
