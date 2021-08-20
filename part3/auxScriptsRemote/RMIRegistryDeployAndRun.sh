cd ..
echo "Transfering data to the RMIregistry node."
sshpass -f password ssh sd308@l040101-ws09.ua.pt 'mkdir -p test/AirLift-RMI'
sshpass -f password ssh sd308@l040101-ws09.ua.pt 'rm -rf test/AirLift-RMI/*'
sshpass -f password ssh sd308@l040101-ws09.ua.pt 'mkdir -p Public/classes/interfaces'
sshpass -f password ssh sd308@l040101-ws09.ua.pt 'rm -rf Public/classes/interfaces/*'
sshpass -f password scp dirRMIRegistry.zip sd308@l040101-ws09.ua.pt:test/AirLift-RMI
echo "Decompressing data sent to the RMIregistry node."
sshpass -f password ssh sd308@l040101-ws09.ua.pt 'cd test/AirLift-RMI ; unzip -uq dirRMIRegistry.zip'
sshpass -f password ssh sd308@l040101-ws09.ua.pt 'cd test/AirLift-RMI/dirRMIRegistry ; cp interfaces/*.class /home/sd308/Public/classes/interfaces ; cp set_rmiregistry_d.sh /home/sd308'
echo "Executing program at the RMIregistry node."
sshpass -f password ssh sd308@l040101-ws09.ua.pt './set_rmiregistry_d.sh sd308 22000'
