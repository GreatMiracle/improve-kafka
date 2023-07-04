terminal:

docker exec -ti kafka bash 

kafka-topics.sh --bootstrap-server localhost:9092 --list

kafka-topics.sh --bootstrap-server localhost:9092 --describe --topic t-comodity-order

