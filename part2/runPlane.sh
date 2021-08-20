echo "Transfering data to the plane node."
sshpass -f password ssh sd308@l040101-ws04.ua.pt 'mkdir -p test/AirLIft'
sshpass -f password ssh sd308@l040101-ws04.ua.pt 'rm -rf test/AirLIft/*'
sshpass -f password scp newGeneratedFolder/dirPlane.zip sd308@l040101-ws04.ua.pt:test/AirLIft
echo "Decompressing data sent to the plane node."
sshpass -f password ssh sd308@l040101-ws04.ua.pt 'cd test/AirLIft ; unzip -uq dirPlane.zip'
echo "Executing program at the plane node."
sshpass -f password ssh sd308@l040101-ws04.ua.pt 'cd test/AirLIft/dirPlane ; java -cp ".:./genclass.jar" serverSide.main.MainPlane 22004 l040101-ws01.ua.pt 22001'