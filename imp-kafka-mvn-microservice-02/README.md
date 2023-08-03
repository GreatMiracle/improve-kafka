câu lệnh tạo partition trong topic:

docker exec -it kafka bash

kafka-topics.sh --bootstrap-server localhost:9092 --create --partitions 2 --replication-factor 1 --topic t-invoice

kafka-topics.sh --bootstrap-server localhost:9092 --create --partitions 2 --replication-factor 1 --topic t-image

kafka-topics.sh --bootstrap-server localhost:9092 --offset earliest --partition 0 --topic t-invoice


kafka-topics.sh --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-order

kafka-topics.sh --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-order-reply

kafka-topics.sh --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-commodity-promotion

