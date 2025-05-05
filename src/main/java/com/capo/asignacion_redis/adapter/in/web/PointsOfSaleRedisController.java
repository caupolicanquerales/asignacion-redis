package com.capo.asignacion_redis.adapter.in.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.capo.asignacion_redis.adapter.in.model.PointsRedisRequestModel;
import com.capo.asignacion_redis.adapter.out.model.PointsOfSaleModel;
import com.capo.asignacion_redis.adapter.out.persistence.redisOperations.OperationPointOfSale;

import reactor.core.publisher.Mono;

@Controller
@RequestMapping("puntos")
public class PointsOfSaleRedisController {
	
	
	@Autowired
	OperationPointOfSale operationInRedis;
	
	@PostMapping("/save")
	public Mono<ResponseEntity<String>> saveAndUpdatePointsOfsale(@RequestBody PointsRedisRequestModel request){
		/*
		return pointsOfSale.updateCostPointsOfSale(request)
				.map(ResponseEntity.ok()::body)
				.switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));*/
		return null;
	}
	
	@PostMapping("/remove")
	public Mono<ResponseEntity<String>> removePointsOfSale(@RequestBody PointsRedisRequestModel request){
		/*
		return pointsOfSale.removePointsOfSale(request)
				.map(ResponseEntity.ok()::body)
				.switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));*/
		return null;
	}
	
	@GetMapping("/points")
	public Mono<ResponseEntity<PointsOfSaleModel>> getPointsOfSale(@RequestBody PointsRedisRequestModel request){
		return operationInRedis.getPointsOfSale()
				.map(ResponseEntity.ok()::body)
				.switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
	}
	
	@PostMapping("/save-point")
	public Mono<ResponseEntity<String>> savePointsOfsale(@RequestBody PointsRedisRequestModel request){
		/*
		return pointsOfSale.savePointsOfSale(request)
				.map(ResponseEntity.ok()::body)
				.switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));*/
		return null;
	}
}
