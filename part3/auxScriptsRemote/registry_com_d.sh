CODEBASE="http://l040101-ws09.ua.pt/"$1"/classes/"
java -cp ".:./genclass.jar:../.." -Djava.rmi.server.codebase=$CODEBASE\
     -Djava.rmi.server.useCodebaseOnly=true\
     -Djava.security.policy=java.policy\
     serverSide.main.ServerRegisterRemoteObject 22001 l040101-ws09.ua.pt 22000