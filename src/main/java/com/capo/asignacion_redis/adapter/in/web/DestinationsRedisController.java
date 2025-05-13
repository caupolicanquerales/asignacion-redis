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
import com.capo.asignacion_redis.adapter.out.buildingGraph.BuildingGraphDestination;
import com.capo.asignacion_redis.adapter.out.dijkstra.Dijkstra;
import com.capo.asignacion_redis.adapter.out.model.DestinationsModel;
import com.capo.asignacion_redis.adapter.out.model.ResponseGraphRedisModel;
import com.capo.asignacion_redis.adapter.out.operationDestination.OperationsDestination;
import com.capo.asignacion_redis.adapter.out.persistence.redisOperations.OperationDestinationInRedis;

import reactor.core.publisher.Mono;

@Controller
@RequestMapping("costos")
public class DestinationsRedisController {
	
	@Autowired
	OperationDestinationInRedis costAndRoute;
	
	@Autowired
	BuildingGraphDestination buildingGraph;
	
	@Autowired
	Dijkstra dijkstra;
	
	@Autowired
	OperationsDestination operationsDestination;
	
	@PostMapping("/update-cost")
	public Mono<ResponseEntity<String>> updateCostInDestination(@RequestBody DestinationModel request){
		return operationsDestination.updateCostInDestination(request)
				.map(ResponseEntity.ok()::body)
				.switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
	}
	
	@DeleteMapping("/remove")
	public Mono<ResponseEntity<String>> removeCostAndDestination(@RequestBody DestinationModel request){
		return operationsDestination.removeDestination(request)
				.map(ResponseEntity.ok()::body)
				.switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
	}
	
	@GetMapping("/prices")
	public Mono<ResponseEntity<ResponseGraphRedisModel>> getPricesFromVertex(@RequestBody DestinationModel request){
		return dijkstra.estimationOfCosts(request)
				.map(ResponseEntity.ok()::body)
				.switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
	}
	
	@GetMapping("/build")
	public Mono<ResponseEntity<String>> buildingGraph(@RequestBody DestinationModel request){
		return buildingGraph.buildingGraph()
				.map(ResponseEntity.ok()::body)
				.switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
	}
	
	@GetMapping("/destinations")
	public Mono<ResponseEntity<DestinationsModel>> getAllCostsAndDestinations(@RequestBody DestinationModel request){
		return costAndRoute.getAllCostsAndDestinations()
				.map(ResponseEntity.ok()::body)
				.switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
	}
	
	@PostMapping("/save-destination")
	public Mono<ResponseEntity<String>> saveCostAndDestination(@RequestBody DestinationModel request){
		return operationsDestination.saveDestination(request)
				.map(ResponseEntity.ok()::body)
				.switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
	}
}
