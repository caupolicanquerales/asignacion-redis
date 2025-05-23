package com.capo.asignacion_redis.adapter.out.buildingGraph;

import java.util.Map.Entry;

import org.springframework.stereotype.Service;

import com.capo.asignacion_redis.adapter.out.persistence.redisOperations.OperationDestinationInRedis;
import com.capo.asignacion_redis.adapter.out.persistence.redisOperations.OperationPointOfSaleInRedis;
import com.capo.asignacion_redis.application.port.in.CostsAndPointsMaps;

import reactor.core.publisher.Flux;

@Service
public class EstablishInformationToGraph implements CostsAndPointsMaps {
	
	private final OperationPointOfSaleInRedis operationPointOfSale;
	private final OperationDestinationInRedis operationDestination;
	
	public EstablishInformationToGraph(OperationPointOfSaleInRedis operationPointOfSale,
			OperationDestinationInRedis operationDestination) {
		this.operationPointOfSale= operationPointOfSale;
		this.operationDestination= operationDestination;
	}
	
	@Override
	public Flux<Entry<String, String>> getMapforDestinationAndCost() {
		return operationDestination.getMapCost();
	}

	@Override
	public Flux<Entry<String, String>> getMapforPointsOfSale() {
		return operationPointOfSale.getMapStores();
	}
}
