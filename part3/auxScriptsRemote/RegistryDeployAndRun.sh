cd ..
echo "Transfering data to the registry node."
sshpass -f password ssh sd308@l040101-ws09.ua.pt 'mkdir -p test/AirLift-RMI'
sshpass -f password scp dirRegistry.zip sd308@l040101-ws09.ua.pt:test/AirLift-RMI
echo "Decompressing data sent to the registry node."
sshpass -f password ssh sd308@l040101-ws09.ua.pt 'cd test/AirLift-RMI ; unzip -uq dirRegistry.zip'
echo "Executing program at the registry node."
sshpass -f password ssh sd308@l040101-ws09.ua.pt 'cd test/AirLift-RMI/dirRegistry ; ./registry_com_d.sh sd308'
