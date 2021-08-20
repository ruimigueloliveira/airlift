cd ..
echo "Transfering data to the Pilot node."
sshpass -f password ssh sd308@l040101-ws04.ua.pt 'mkdir -p test/AirLift-RMI'
sshpass -f password ssh sd308@l040101-ws04.ua.pt 'rm -rf test/AirLift-RMI/*'
sshpass -f password scp dirPilot.zip sd308@l040101-ws04.ua.pt:test/AirLift-RMI
echo "Decompressing data sent to the Pilot node."
sshpass -f password ssh sd308@l040101-ws04.ua.pt 'cd test/AirLift-RMI ; unzip -uq dirPilot.zip'
echo "Executing program at the Pilot node."
sshpass -f password ssh sd308@l040101-ws04.ua.pt 'cd test/AirLift-RMI/dirPilot ; ./pilot_com_d.sh'
