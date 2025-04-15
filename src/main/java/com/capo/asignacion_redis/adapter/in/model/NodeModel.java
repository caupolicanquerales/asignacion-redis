package com.capo.asignacion_redis.adapter.in.model;

import java.io.Serializable;

public class NodeModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int vertex;
	private int weight;
	
	public NodeModel() {
	}
	
	public NodeModel(int vertex,int weight) {
		this.vertex=vertex;
		this.weight=weight;
	}

	public int getVertex() {
		return vertex;
	}

	public void setVertex(int vertex) {
		this.vertex = vertex;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
}
