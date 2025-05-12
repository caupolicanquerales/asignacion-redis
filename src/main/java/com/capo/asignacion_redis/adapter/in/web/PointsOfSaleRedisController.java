package com.capo.asignacion_redis.adapter.in.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.capo.asignacion_redis.adapter.in.model.PointRedisModel;
import com.capo.asignacion_redis.adapter.out.model.PointsOfSaleModel;
import com.capo.asignacion_redis.adapter.out.operationPointOfSale.OperationsPointOfSale;
import com.capo.asignacion_redis.adapter.out.persistence.redisOperations.OperationPointOfSaleInRedis;

import reactor.core.publisher.Mono;

@Controller
@RequestMapping("puntos")
public class PointsOfSaleRedisController {
	
	@Autowired
	OperationPointOfSaleInRedis operationInRedis;
	
	@Autowired
	OperationsPointOfSale operationPointOfSale;
	
	@PostMapping("/update-location")
	public Mono<ResponseEntity<String>> updateLocationPointsOfsale(@RequestBody PointRedisModel request){
		return operationPointOfSale.udpateLocationPointOfSale(request)
				.map(ResponseEntity.ok()::body)
				.switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
	}
	
	@PostMapping("/remove")
	public Mono<ResponseEntity<String>> removePointsOfSale(@RequestBody PointRedisModel request){
		return operationPointOfSale.removePointOfSale(request)
				.map(ResponseEntity.ok()::body)
				.switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
	}
	
	@GetMapping("/points")
	public Mono<ResponseEntity<PointsOfSaleModel>> getPointsOfSale(@RequestBody PointRedisModel request){
		return operationInRedis.getPointsOfSale()
				.map(ResponseEntity.ok()::body)
				.switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
	}
	
	@PostMapping("/save-point")
	public Mono<ResponseEntity<String>> savePointsOfsale(@RequestBody PointRedisModel request){
		return operationPointOfSale.savePointOfSale(request)
				.map(ResponseEntity.ok()::body)
				.switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
	}
}
