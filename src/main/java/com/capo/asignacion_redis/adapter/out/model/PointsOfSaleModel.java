package com.capo.asignacion_redis.adapter.out.model;

import java.util.List;

public class PointsOfSaleModel {
	private List<PointRedisModel> pointOfSales;

	public List<PointRedisModel> getPointOfSales() {
		return pointOfSales;
	}

	public void setPointOfSales(List<PointRedisModel> pointOfSales) {
		this.pointOfSales = pointOfSales;
	}
	
}
