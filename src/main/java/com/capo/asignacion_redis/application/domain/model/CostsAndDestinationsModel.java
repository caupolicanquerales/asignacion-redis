package com.capo.asignacion_redis.application.domain.model;

import java.util.List;


public class CostsAndDestinationsModel {
	
	private List<PointsAndCostModel> costAndDestination;

	public List<PointsAndCostModel> getCostAndDestination() {
		return costAndDestination;
	}

	public void setCostAndDestination(List<PointsAndCostModel> costAndDestination) {
		this.costAndDestination = costAndDestination;
	}
}
