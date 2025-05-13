package com.capo.asignacion_redis.adapter.in.preload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.capo.adapter.kafkaEvents.RedisDestinationEvent;
import com.capo.asignacion_redis.adapter.in.file.RecoverFileFromResource;
import com.capo.asignacion_redis.adapter.in.model.DestinationModel;
import com.capo.asignacion_redis.adapter.mappers.MapperRedisPointOfSale;
import com.capo.asignacion_redis.adapter.out.emitEvents.EmitingEvent;
import com.capo.asignacion_redis.adapter.out.model.DestinationsModel;
import com.capo.asignacion_redis.adapter.out.model.VertexRedisModel;
import com.capo.asignacion_redis.adapter.out.persistence.startingApp.OperationsInRedisStarting;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Flux;

@Component
public class PreloadDestinationAndCost implements CommandLineRunner{
	
	@Autowired
	EmitingEvent<RedisDestinationEvent> emitEvent;
	
	@Autowired
	OperationsInRedisStarting operationsInCost;
	
	@Autowired
	RecoverFileFromResource recoverFile;
	
	private static final String DESTINATIONS="destinations";
	private static final String FILE_COSTS="/information/cost_and_destination_point_of_sales.json";

	
	@Override
	public void run(String... args) throws Exception {
		for(String arg: args) {
			if(arg.equals(DESTINATIONS)) {
				DestinationsModel destinations= getFileFromResourceCostAndDestination(FILE_COSTS);
				Flux.fromIterable(destinations.getCostAndDestination())
				.map(this::savingDestinationInRedis)
				.map(model-> MapperRedisPointOfSale.mapperDestinationEvent(model))
				.doOnNext(event->emitEvent.emit(event))
				.subscribe();
				
				/*
				PointsOfSaleModel pointsOfSale= getFileFromResourcePointsOfSale(FILE_POINTS);
				Flux.fromIterable(pointsOfSale.getPointOfSales())
				.map(this::savingPointOfSalesInRedis)
				.map(model-> MapperRedisEvent.mapperPointOfSaleEvent(model))
				//.map(event-> convertEventToJson(event))
				.doOnNext(event->emitEvent.emit(event))
				.subscribe();
				*/
				
				/*
				destinationPointOfSales.findAll().hasElements().map(elements->{
					if(elements) {
						return destinationPointOfSales.findAll()
							.map(this::savingDestinationInRedis).subscribe();
					}else {
						return Flux.fromIterable(destinations.getCostAndDestination())
								.map(this::getDestinationPointOfSalesMongo)
								.flatMap(destination->destinationPointOfSales.save(destination))
								.map(this::savingDestinationInRedis).subscribe();
					}
				}).subscribe();*/
			}
		}
	}
	
	
	private DestinationsModel getFileFromResourceCostAndDestination(String address) throws Exception{
		String json= recoverFile.getFileInString(address);
		ObjectMapper mapper = new ObjectMapper();
		DestinationsModel destinations= mapper.readValue(json, DestinationsModel.class);
		return destinations;
	}
	
	/*
	private DestinationPointOfSalesMongo getDestinationPointOfSalesMongo(Destination destination) {
		DestinationPointOfSalesMongo destinationMongo = new DestinationPointOfSalesMongo();
		destinationMongo.setDestination(destination);
		return destinationMongo;
	}
	
	private Mono<String> savingDestinationInRedis(DestinationPointOfSalesMongo response) {
		VertexRedisRequest vertexRedis= new VertexRedisRequest();
		vertexRedis.setStartVertex(response.getDestination().getStartVertex());
		vertexRedis.setEndVertex(response.getDestination().getEndVertex());
		vertexRedis.setCost(response.getDestination().getCost());
		costAndRouteRedis.saveAndUpdateCostAndDestinationStartingApp(vertexRedis);
		return Mono.just("OK");
	}*/
	
	private VertexRedisModel savingDestinationInRedis(DestinationModel response) {
		VertexRedisModel vertexRedis= new VertexRedisModel();
		vertexRedis.setStartVertex(response.getStartVertex());
		vertexRedis.setEndVertex(response.getEndVertex());
		vertexRedis.setCost(response.getCost());
		operationsInCost.saveAndUpdateCostAndDestinationStartingApp(vertexRedis);
		return vertexRedis;
	}
}
