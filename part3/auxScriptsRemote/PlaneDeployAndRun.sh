cd ..
echo "Transfering data to the Plane node."
sshpass -f password ssh sd308@l040101-ws05.ua.pt 'mkdir -p test/AirLift-RMI'
sshpass -f password ssh sd308@l040101-ws05.ua.pt 'rm -rf test/AirLift-RMI/*'
sshpass -f password scp dirPlane.zip sd308@l040101-ws05.ua.pt:test/AirLift-RMI
echo "Decompressing data sent to the Plane node."
sshpass -f password ssh sd308@l040101-ws05.ua.pt 'cd test/AirLift-RMI ; unzip -uq dirPlane.zip'
echo "Executing program at the Plane node."
sshpass -f password ssh sd308@l040101-ws05.ua.pt 'cd test/AirLift-RMI/dirPlane ; ./plane_com_d.sh sd308'
