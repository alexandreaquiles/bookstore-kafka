#https://kafka.apache.org/downloads 
#Scala 2.13 - kafka_2.13-2.5.0.tgz (asc, sha512) 

bin/zookeeper-server-start.sh config/zookeeper.properties 

bin/kafka-server-start.sh config/server.properties 

bin/kafka-console-producer.sh --bootstrap-server localhost:9092 --topic ebooks

bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic ebooks

bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic ebooks --from-beginning

bin/kafka-topics.sh --bootstrap-server localhost:9092 --list
bin/kafka-topics.sh --bootstrap-server localhost:9092 --create --topic emails
bin/kafka-topics.sh --bootstrap-server localhost:9092 --describe --topic emails

bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic ebooks --from-beginning --group ebook-generators

bin/kafka-consumer-groups.sh --bootstrap-server localhost:9092 --list
bin/kafka-consumer-groups.sh --bootstrap-server localhost:9092 --describe --group ebook-generators

bin/kafka-console-producer.sh --bootstrap-server localhost:9092 --topic ebooks --property "parse.key=true" --property "key.separator=;"

bin/kafka-topics.sh --bootstrap-server localhost:9092 --create --topic ebooks --partitions 2

bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic first-topic --partition 0 --offset 10