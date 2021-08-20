cd ..
echo "Transfering data to the general repository node."
sshpass -f password ssh sd308@l040101-ws08.ua.pt 'mkdir -p test/AirLift-RMI'
sshpass -f password ssh sd308@l040101-ws08.ua.pt 'rm -rf test/AirLift-RMI/*'
sshpass -f password scp dirGeneralRepos.zip sd308@l040101-ws08.ua.pt:test/AirLift-RMI
echo "Decompressing data sent to the general repository node."
sshpass -f password ssh sd308@l040101-ws08.ua.pt 'cd test/AirLift-RMI ; unzip -uq dirGeneralRepos.zip'
echo "Executing program at the general repository node."
sshpass -f password ssh sd308@l040101-ws08.ua.pt 'cd test/AirLift-RMI/dirGeneralRepos ; ./general_repos_com_d.sh sd308'
echo "Server shutdown."
sshpass -f password ssh sd308@l040101-ws08.ua.pt 'cd test/AirLift-RMI/dirGeneralRepos ; less logger'
