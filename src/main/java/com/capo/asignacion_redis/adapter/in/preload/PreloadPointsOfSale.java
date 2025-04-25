package com.capo.asignacion_redis.adapter.in.preload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.capo.asignacion_redis.adapter.in.file.RecoverFileFromResource;
import com.capo.asignacion_redis.adapter.out.model.PointRedisModel;
import com.capo.asignacion_redis.adapter.out.model.PointsOfSaleModel;
import com.capo.asignacion_redis.adapter.out.persistence.startingApp.OperationsInRedisStarting;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class PreloadPointsOfSale implements CommandLineRunner {
	
	/*
	@Autowired
	PointOfSaleMongoRepository pointOfSaleMongo;*/
	
	@Autowired
	OperationsInRedisStarting operationsInCost;
	
	@Autowired
	RecoverFileFromResource recoverFile;
	
	private static final String POINTS="points";
	private static final String FILE_POINTS="/information/point_of_sales.json";
	
	@Override
	public void run(String... args) throws Exception {
		for(String arg: args) {
			if(arg.equals(POINTS)) {
				PointsOfSaleModel pointsOfSale= getFileFromResourcePointsOfSale(FILE_POINTS);
				Flux.fromIterable(pointsOfSale.getPointOfSales())
				//.map(this::getPointOfSalesInMongo)
				//.flatMap(destination->pointOfSaleMongo.save(destination))
				.map(this::savingPointOfSalesInRedis).subscribe();
				
				
				/*
				pointOfSaleMongo.findAll().hasElements().map(elements->{
					if(elements) {
						return pointOfSaleMongo.findAll()
								.map(this::savingPointOfSalesInRedis).subscribe();
					}else {
						return Flux.fromIterable(pointOfSales.getPointOfSales())
								.map(this::getPointOfSalesInMongo)
								.flatMap(destination->pointOfSaleMongo.save(destination))
								.map(this::savingPointOfSalesInRedis).subscribe();
					}
				}).subscribe();*/
			}
		}
	}
	
	private PointsOfSaleModel getFileFromResourcePointsOfSale(String address) throws Exception{
		String json= recoverFile.getFileInString(address);
		ObjectMapper mapper = new ObjectMapper();
		PointsOfSaleModel points= mapper.readValue(json, PointsOfSaleModel.class);
		return points;
	}
	
	/*
	private PointOfSalesInMongo getPointOfSalesInMongo(Point point) {
		PointOfSalesInMongo pointsInMongo= new PointOfSalesInMongo();
		pointsInMongo.setPoint(point);
		return pointsInMongo;
	}
	private Mono<String> savingPointOfSalesInRedis(PointOfSalesInMongo response) {
		PointsRedisRequest pointsRedis= new PointsRedisRequest();
		pointsRedis.setId(response.getPoint().getId());
		pointsRedis.setLocation(response.getPoint().getLocation());
		pointsOfSaleRedis.savePointsOfSaleStartingApp(pointsRedis);
		return Mono.just("OK");
	}*/
	
	private Mono<String> savingPointOfSalesInRedis(PointRedisModel response) {
		operationsInCost.savePointsOfSaleStartingApp(response);
		return Mono.just("OK");
	}
}
