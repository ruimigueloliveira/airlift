echo "Transfering data to the Pilot node."
sshpass -f password ssh sd308@l040101-ws06.ua.pt 'mkdir -p test/AirLIft'
sshpass -f password ssh sd308@l040101-ws06.ua.pt 'rm -rf test/AirLIft/*'
sshpass -f password scp newGeneratedFolder/dirPilot.zip sd308@l040101-ws06.ua.pt:test/AirLIft
echo "Decompressing data sent to the Pilot node."
sshpass -f password ssh sd308@l040101-ws06.ua.pt 'cd test/AirLIft ; unzip -uq dirPilot.zip'
echo "Executing program at the Pilot node."
sshpass -f password ssh sd308@l040101-ws06.ua.pt 'cd test/AirLIft/dirPilot ; java -cp ".:./genclass.jar" clientSide.main.MainPilot l040101-ws02.ua.pt 22002 l040101-ws04.ua.pt 22004 l040101-ws03.ua.pt 22003'