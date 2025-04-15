package com.capo.asignacion_redis.adapter.enums;

public enum RedisEnum {
	MAP_COST("costos"),
	MAP_STORES("puntos"),
	GRAPH("graph");
	
	public final String value;
	
	RedisEnum(String value) {
		this.value=value;
	}
}
