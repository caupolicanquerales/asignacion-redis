package com.capo.asignacion_redis.adapter.utils;

import reactor.core.publisher.Sinks;

public class CreationOfSink {
	
	public static <T> Sinks.Many<T> instantiateSinks(Class<T> clazz){
		Sinks.Many<T> sink= Sinks.many().unicast().onBackpressureBuffer();
		return sink;
	}
}
