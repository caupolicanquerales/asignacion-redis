package com.capo.asignacion_redis.adapter.out.emitEvents;

import reactor.core.publisher.Flux;

public interface EventInUse<T> {
	Flux<T> publish();
}
