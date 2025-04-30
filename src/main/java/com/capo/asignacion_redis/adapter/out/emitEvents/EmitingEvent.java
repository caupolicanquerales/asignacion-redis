package com.capo.asignacion_redis.adapter.out.emitEvents;

public interface EmitingEvent<T> {
	void emit(T event);
}
