package com.capo.asignacion_redis.application.port.out;

import java.util.Map;

import reactor.core.publisher.Flux;

public interface CostsAndPointsMaps {
	Flux<Map.Entry<String, String>> getMapforDestinationAndCost();
	Flux<Map.Entry<String, String>> getMapforPointsOfSale();
}
