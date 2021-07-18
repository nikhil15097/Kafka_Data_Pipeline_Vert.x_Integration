package com.kafka.kafkaconnect;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.kafka.client.consumer.KafkaConsumer;

public class Consumer {
	
	public static void kafkaConsumer(Vertx vertx, HttpServerResponse response) {
		Map<String, String> config = new HashMap<>();
		config.put("bootstrap.servers", "localhost:9092");
		config.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		config.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		config.put("group.id", "my_group");
		config.put("auto.offset.reset", "earliest");
		config.put("enable.auto.commit", "false");

		// use consumer for interacting with Apache Kafka
		KafkaConsumer<String, String> consumer = KafkaConsumer.create(vertx, config);
		
		
		
		consumer
		  .subscribe("test")
		  .onSuccess(v ->
		    System.out.println("subscribed")
		  ).onFailure(cause ->
		    System.out.println("Could not subscribe " + cause.getMessage())
		  );
		 ArrayList<String> al = new ArrayList<String>();
		 
		consumer.handler(rec -> {
			  
			  String s=rec.value().toString();
			  System.out.print(s+" ");
			  //response.write(s);
			});
		
		
			
		  Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		  String timestamps = timestamp.toString();
		response.write("Last Message consumed at: "+timestamps);
		
		
	}


}
