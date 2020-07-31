package br.com.alura.ebooks.consumers;

import br.com.alura.ebooks.domain.Book;
import br.com.alura.ebooks.domain.Order;
import br.com.alura.ebooks.serialization.JsonDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.Properties;

public class EbookGeneratorService {

    public static void main(String[] args) {

        KafkaConsumer<String, Order> consumer = new KafkaConsumer<>(properties());

        consumer.subscribe(Collections.singletonList("bookstore_ebooks"));

        while (true) {
            ConsumerRecords<String, Order> records = consumer.poll(Duration.ofMillis(1000)); //busy wait

            // System.out.println("Found " + records.count() + " records.");
            for (ConsumerRecord<String, Order> record : records) {
                System.out.println(record.key() + "\n" +
                        record.value() + "\n" +
                        Instant.ofEpochMilli(record.timestamp()) + "\n" +
                        record.topic() + ", " + record.partition() + ", " + record.offset() + "\n");

                Order order = record.value();
                System.out.println("Generating ebook for customer: " + order.getClientId());

                Book book = order.getBook();
                System.out.println("Book: " + book.getName());
                System.out.println("Authors: " + book.getAuthors());
            }
        }

    }

    private static Properties properties() {
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "ebooks-generators");
        properties.setProperty(JsonDeserializer.TYPE_CONFIG, Order.class.getName());
        return properties;
    }

}
