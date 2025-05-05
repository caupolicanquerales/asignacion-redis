package com.capo.asignacion_redis.adapter.out.model;

import java.util.List;

import com.capo.asignacion_redis.adapter.in.model.DestinationModel;

public class DestinationsModel {
	
	private List<DestinationModel> costAndDestination;

	public List<DestinationModel> getCostAndDestination() {
		return costAndDestination;
	}

	public void setCostAndDestination(List<DestinationModel> costAndDestination) {
		this.costAndDestination = costAndDestination;
	}
}
