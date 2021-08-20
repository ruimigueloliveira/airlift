echo "Transfering data to the Hostess node."
sshpass -f password ssh sd308@l040101-ws05.ua.pt 'mkdir -p test/AirLIft'
sshpass -f password ssh sd308@l040101-ws05.ua.pt 'rm -rf test/AirLIft/*'
sshpass -f password scp newGeneratedFolder/dirHostess.zip sd308@l040101-ws05.ua.pt:test/AirLIft
echo "Decompressing data sent to the Hostess node."
sshpass -f password ssh sd308@l040101-ws05.ua.pt 'cd test/AirLIft ; unzip -uq dirHostess.zip'
echo "Executing program at the Hostess node."
sshpass -f password ssh sd308@l040101-ws05.ua.pt 'cd test/AirLIft/dirHostess ; java -cp ".:./genclass.jar" clientSide.main.MainHostess l040101-ws02.ua.pt 22002'