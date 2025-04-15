package com.capo.asignacion_redis.adapter.in.model;

import java.util.List;
import java.util.Map;

public class GraphObjectModel {
	
	private Map<String,List<NodeModel>> graphObject;

	public Map<String, List<NodeModel>> getGraphObject() {
		return graphObject;
	}

	public void setGraphObject(Map<String, List<NodeModel>> graphObject) {
		this.graphObject = graphObject;
	}
}
