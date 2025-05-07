package com.capo.asignacion_redis.application.port.in;

import java.util.Map;

import reactor.core.publisher.Flux;

public interface CostsAndPointsMaps {
	Flux<Map.Entry<String, String>> getMapforDestinationAndCost();
	Flux<Map.Entry<String, String>> getMapforPointsOfSale();
}
