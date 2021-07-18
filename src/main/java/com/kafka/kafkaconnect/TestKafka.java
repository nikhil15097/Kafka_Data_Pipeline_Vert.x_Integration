package com.kafka.kafkaconnect;


import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;

public class TestKafka {

	public static void main(String[] args) {
		Vertx vertx = Vertx.vertx();
		
		HttpServer server = vertx.createHttpServer();
		
		Router router = Router.router(vertx);
		
		Route route = router.route(HttpMethod.POST, "/message/:msg");
		
		 route.handler(ctx -> {
				
			  HttpServerResponse response = ctx.response();
			  response.setChunked(true);
			  String msg = ctx.pathParam("msg");
			  Producer.kafkaProducer(vertx,msg);
			  Consumer.kafkaConsumer(vertx,response);
			 
			  ctx.response().end();
			});
		 server.requestHandler(router).listen(8090);
	}
}
