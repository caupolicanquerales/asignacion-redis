package com.capo.asignacion_redis.adapter.out.operationPointOfSale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.capo.adapter.kafkaEvents.RedisPointOfSaleEvent;
import com.capo.asignacion_redis.adapter.in.file.RecoverFileFromResource;
import com.capo.asignacion_redis.adapter.in.model.PointRedisModel;
import com.capo.asignacion_redis.adapter.mappers.MapperRedisPointOfSale;
import com.capo.asignacion_redis.adapter.out.emitEvents.EmitingEvent;
import com.capo.asignacion_redis.adapter.out.model.PointsOfSaleModel;
import com.capo.asignacion_redis.adapter.out.persistence.startingApp.OperationsInRedisStarting;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Flux;

@Service
public class OperationPointOfSaleStartingAppImpl implements OperationPointOfSaleStarting{
	
	private final EmitingEvent<RedisPointOfSaleEvent> emitEvent;
	private final OperationsInRedisStarting operationsInRedis;
	private final RecoverFileFromResource recoverFile;
	private static final String FILE_POINTS="/information/point_of_sales.json";
	private static final Logger log = LoggerFactory.getLogger(OperationPointOfSaleStartingAppImpl.class);
	
	public OperationPointOfSaleStartingAppImpl(EmitingEvent<RedisPointOfSaleEvent> emitEvent,
			OperationsInRedisStarting operationsInRedis,
			RecoverFileFromResource recoverFile) {
		this.operationsInRedis=operationsInRedis;
		this.recoverFile= recoverFile;
		this.emitEvent= emitEvent;
	}
	
	@Override
	public void saveFileInformationInRedis(){
		PointsOfSaleModel pointsOfSale;
		try {
			pointsOfSale = getFileFromResourcePointsOfSale(FILE_POINTS);
			Flux.fromIterable(pointsOfSale.getPointOfSales())
			.map(this::savingPointOfSalesInRedis)
			.map(model-> MapperRedisPointOfSale.mapperPointOfSaleEvent(model))
			.doOnNext(event->emitEvent.emit(event))
			.subscribe();
		} catch (Exception e) {
			log.info("error in save file information point in Redis {}", e);
		}
	}
	
	private PointsOfSaleModel getFileFromResourcePointsOfSale(String address) throws Exception{
		String json= recoverFile.getFileInString(address);
		ObjectMapper mapper = new ObjectMapper();
		PointsOfSaleModel points= mapper.readValue(json, PointsOfSaleModel.class);
		return points;
	}
	
	@Override
	public PointRedisModel savingPointOfSalesInRedis(PointRedisModel response) {
		PointRedisModel result= operationsInRedis.savePointsOfSaleStartingApp(response);
		return result;
	}
}
