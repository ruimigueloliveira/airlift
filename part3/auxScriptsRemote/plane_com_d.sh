CODEBASE="http://l040101-ws09.ua.pt/Public/classes/"
java -cp ".:./genclass.jar:../.." -Djava.rmi.server.codebase=$CODEBASE\
     -Djava.rmi.server.useCodebaseOnly=false\
     -Djava.security.policy=java.policy\
     serverSide.main.MainPlane 22005 l040101-ws09.ua.pt 22000
