package br.com.alura.ebooks.consumers;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;
import java.util.regex.Pattern;

public class BookstoreAuditingService {

    public static void main(String[] args) {

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties());

//        consumer.subscribe(Arrays.asList("bookstore_ebooks", "bookstore_emails"));
        consumer.subscribe(Pattern.compile("bookstore_.+"));

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));//busy wait
            for (ConsumerRecord<String, String> record : records) {
                System.out.println("Auditing...");
                System.out.println(record.key() + "\n" +
                        record.value() + "\n" +
                        Instant.ofEpochMilli(record.timestamp()) + "\n" +
                        record.topic() + ", " + record.partition() + ", " + record.offset() + "\n");
            }
        }

    }

    private static Properties properties() {
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "bookstore-auditors");
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        return properties;
    }

}
