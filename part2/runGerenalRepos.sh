echo "Transfering data to the repos node."
sshpass -f password ssh sd308@l040101-ws01.ua.pt 'mkdir -p test/AirLIft'
sshpass -f password ssh sd308@l040101-ws01.ua.pt 'rm -rf test/AirLIft/*'
sshpass -f password scp newGeneratedFolder/dirGeneralRepos.zip sd308@l040101-ws01.ua.pt:test/AirLIft
echo "Decompressing data sent to the repos node."
sshpass -f password ssh sd308@l040101-ws01.ua.pt 'cd test/AirLIft ; unzip -uq dirGeneralRepos.zip'
echo "Executing program at the repos node."
sshpass -f password ssh sd308@l040101-ws01.ua.pt 'cd test/AirLIft/dirGeneralRepos ; java -cp ".:./genclass.jar" serverSide.main.MainRepos 22001'