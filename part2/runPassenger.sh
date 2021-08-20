echo "Transfering data to the Passenger node."
sshpass -f password ssh sd308@l040101-ws07.ua.pt 'mkdir -p test/AirLIft'
sshpass -f password ssh sd308@l040101-ws07.ua.pt 'rm -rf test/AirLIft/*'
sshpass -f password scp newGeneratedFolder/dirPassenger.zip sd308@l040101-ws07.ua.pt:test/AirLIft
echo "Decompressing data sent to the Passenger node."
sshpass -f password ssh sd308@l040101-ws07.ua.pt 'cd test/AirLIft ; unzip -uq dirPassenger.zip'
echo "Executing program at the Passenger node."
sshpass -f password ssh sd308@l040101-ws07.ua.pt 'cd test/AirLIft/dirPassenger ; java -cp ".:./genclass.jar" clientSide.main.MainPassenger l040101-ws02.ua.pt 22002 l040101-ws04.ua.pt 22004'