package com.capo.asignacion_redis.adapter.in.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.capo.asignacion_redis.adapter.in.model.DestinationModel;
import com.capo.asignacion_redis.adapter.out.model.DestinationsModel;
import com.capo.asignacion_redis.adapter.out.model.ResponseGraphRedisModel;
import com.capo.asignacion_redis.adapter.out.persistence.redisOperations.OperationDestination;

import reactor.core.publisher.Mono;

@Controller
@RequestMapping("costos")
public class CostPointsOfSaleRedisController {
	
	@Autowired
	OperationDestination costAndRoute;
	
	@PostMapping("/update-cost")
	public Mono<ResponseEntity<String>> updateCostAndDestination(@RequestBody DestinationModel request){
		/*
		return costAndRoute.updateCost(request)
				.map(ResponseEntity.ok()::body)
				.switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));*/
		return null;
	}
	
	@DeleteMapping("/delete")
	public Mono<ResponseEntity<String>> deleteCostAndDestination(@RequestBody DestinationModel request){
		/*
		return costAndRoute.deleteCostAndDestination(request)
				.map(ResponseEntity.status(HttpStatus.OK)::body)
				.switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));*/
		return null;
	}
	
	@GetMapping("/prices")
	public Mono<ResponseEntity<ResponseGraphRedisModel>> getPricesFromVertex(@RequestBody DestinationModel request){
		/*
		return costAndRoute.estimationOfCosts(request.getStartVertex())
				.map(ResponseEntity.ok()::body)
				.switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));*/
		return null;
	}
	
	@GetMapping("/build")
	public Mono<ResponseEntity<String>> buildingGraph(@RequestBody DestinationModel request){
		/*
		return costAndRoute.buildingGraph()
				.map(ResponseEntity.ok()::body)
				.switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));*/
		
		return null;
	}
	
	@GetMapping("/destinations")
	public Mono<ResponseEntity<DestinationsModel>> getAllCostsAndDestinations(@RequestBody DestinationModel request){
		return costAndRoute.getAllCostsAndDestinations()
				.map(ResponseEntity.ok()::body)
				.switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
	}
	
	@PostMapping("/save-destination")
	public Mono<ResponseEntity<String>> saveCostAndDestination(@RequestBody DestinationModel request){
		/*
		return costAndRoute.saveCostAndDestination(request)
				.map(ResponseEntity.ok()::body)
				.switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));*/
		return null;
	}
}
