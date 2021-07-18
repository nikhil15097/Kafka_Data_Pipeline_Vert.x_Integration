package com.kafka.kafkaconnect;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import io.vertx.core.Vertx;
import io.vertx.kafka.client.producer.KafkaProducer;
import io.vertx.kafka.client.producer.KafkaProducerRecord;

public class Producer {
	
	public static void kafkaProducer(Vertx vertx, String message) {
		Map<String, String> config = new HashMap<>();
		config.put("bootstrap.servers", "localhost:9092");
		config.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		config.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		config.put("acks", "1");

		// use producer for interacting with Apache 
		KafkaProducer<String, String> producer = KafkaProducer.create(vertx, config);

		KafkaProducerRecord<String, String> record =
			    KafkaProducerRecord.create("test", message);
		producer.write(record);
		

	}
}
