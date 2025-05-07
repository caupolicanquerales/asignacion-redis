package com.capo.asignacion_redis.adapter.out.dijkstra;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.capo.asignacion_redis.adapter.enums.RedisEnum;
import com.capo.asignacion_redis.adapter.in.model.DestinationModel;
import com.capo.asignacion_redis.adapter.out.model.ResponseGraphRedisModel;
import com.capo.asignacion_redis.adapter.out.persistence.redisPetition.BasicPetitionToRedis;
import com.capo.asignacion_redis.application.domain.model.GraphModel;
import com.capo.asignacion_redis.application.domain.service.Dijkstra.DijkstraAlgorithm;
import com.capo.asignacion_redis.application.domain.service.Dijkstra.DijkstraAlgorithmImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Mono;

@Service
public class DijkstraImpl implements Dijkstra {
	
	private final BasicPetitionToRedis petitionRedis; 
	private DijkstraAlgorithm dijkstraAlgorithm;
	private ObjectMapper mapper= new ObjectMapper();
	
	public DijkstraImpl(BasicPetitionToRedis petitionRedis) {
		this.petitionRedis= petitionRedis;
	}
	
	@Override
	public Mono<ResponseGraphRedisModel> estimationOfCosts(DestinationModel destination) {
		dijkstraAlgorithm = new DijkstraAlgorithmImpl();
		return this.petitionRedis.getReactiveJsonBucket(RedisEnum.GRAPH.value)
			.map(graph->mapper.convertValue(graph, GraphModel.class))
			.map(graph->dijkstraAlgorithm.dijkstra(graph, Integer.valueOf(destination.getStartVertex())))
			.map(this::getResponseGraph);
	}
	
	private ResponseGraphRedisModel getResponseGraph(Map<String,Map<Integer,String>> resultDij) {
		ResponseGraphRedisModel result = new ResponseGraphRedisModel();
		result.setResult(resultDij);
		return result;
	}
}
