package com.capo.asignacion_redis.adapter.out.model;

import java.util.List;

import com.capo.asignacion_redis.adapter.in.model.PointRedisModel;

public class PointsOfSaleModel {
	private List<PointRedisModel> pointOfSales;

	public List<PointRedisModel> getPointOfSales() {
		return pointOfSales;
	}

	public void setPointOfSales(List<PointRedisModel> pointOfSales) {
		this.pointOfSales = pointOfSales;
	}
	
}
