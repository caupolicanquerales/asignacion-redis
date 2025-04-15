package com.capo.asignacion_redis.application.domain.model;

import java.util.List;
import java.util.Map;

public class GraphModel {
	
	private Map<String,List<Node>> graphObject;

	public Map<String, List<Node>> getGraphObject() {
		return graphObject;
	}

	public void setGraphObject(Map<String, List<Node>> graphObject) {
		this.graphObject = graphObject;
	}
}
