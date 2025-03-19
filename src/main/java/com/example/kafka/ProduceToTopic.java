package com.example.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class ProduceToTopic {

    public static void main(String[] args) {
        if (args.length < 6 || !args[0].equals("--topic") || !args[2].equals("--key") || !args[4].equals("--value")) {
            System.out.println("Usage: java ProduceToTopic --topic <topic-name> --key <topickey> --value <topicvalue>");
            return;
        }

        String topic = args[1];
        String key = args[3];
        String value = args[5];

        Properties props = new Config().build();
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

        try (KafkaProducer<String, String> producer = new KafkaProducer<>(props)) {
            ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, value);
            RecordMetadata metadata = producer.send(record).get();

            System.out.printf("Produced message: Topic = %s, Key = %s, Value = %s, Partition = %d, Offset = %d\n",
                    topic, key, value, metadata.partition(), metadata.offset());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
