package com.capo.asignacion_redis.application.port.out;

import java.util.Map;

import reactor.core.publisher.Flux;

public interface InputPetitionToGetInformation {
	Flux<Map.Entry<String, String>> getAllCostsAndDestinations();
}
