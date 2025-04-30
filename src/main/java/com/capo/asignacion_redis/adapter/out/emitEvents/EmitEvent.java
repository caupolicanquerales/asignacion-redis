package com.capo.asignacion_redis.adapter.out.emitEvents;

import reactor.core.publisher.Sinks;
import org.springframework.messaging.Message;

public interface EmitEvent {
	<T> void emitEvent(Sinks.Many<T> sink, T event);
}
