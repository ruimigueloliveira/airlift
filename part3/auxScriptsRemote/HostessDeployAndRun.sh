cd ..
echo "Transfering data to the Hostess node."
sshpass -f password ssh sd308@l040101-ws03.ua.pt 'mkdir -p test/AirLift-RMI'
sshpass -f password ssh sd308@l040101-ws03.ua.pt 'rm -rf test/AirLift-RMI/*'
sshpass -f password scp dirHostess.zip sd308@l040101-ws03.ua.pt:test/AirLift-RMI
echo "Decompressing data sent to the Hostess node."
sshpass -f password ssh sd308@l040101-ws03.ua.pt 'cd test/AirLift-RMI ; unzip -uq dirHostess.zip'
echo "Executing program at the Hostess node."
sshpass -f password ssh sd308@l040101-ws03.ua.pt 'cd test/AirLift-RMI/dirHostess ; ./hostess_com_d.sh'
