package com.capo.asignacion_redis.adapter.out.emitEvents;

public interface EventInUse<T> {
	 void publishing(T event);
}
