package com.capo.asignacion_redis.adapter.out.buildingGraph;

import java.util.Map.Entry;

import org.springframework.stereotype.Service;

import com.capo.asignacion_redis.adapter.out.persistence.redisOperations.OperationDestination;
import com.capo.asignacion_redis.adapter.out.persistence.redisOperations.OperationPointOfSale;
import com.capo.asignacion_redis.application.port.in.CostsAndPointsMaps;

import reactor.core.publisher.Flux;

@Service
public class EstablishInformationToGraph implements CostsAndPointsMaps {
	
	private final OperationPointOfSale operationPointOfSale;
	private final OperationDestination operationDestination;
	
	public EstablishInformationToGraph(OperationPointOfSale operationPointOfSale,
			OperationDestination operationDestination) {
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
