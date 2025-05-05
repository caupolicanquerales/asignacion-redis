package com.capo.asignacion_redis.adapter.out.model;

import java.util.Map;

public class ResponseGraphRedisModel {
	private Map<String,Map<Integer,String>> result;

	public Map<String, Map<Integer, String>> getResult() {
		return result;
	}

	public void setResult(Map<String, Map<Integer, String>> result) {
		this.result = result;
	}
}
