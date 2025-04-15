package com.capo.asignacion_redis.application.domain.service.Dijkstra;

import java.util.List;
import java.util.Map;

import com.capo.asignacion_redis.application.domain.model.Node;


public interface DijkstraAlgorithmRedis {
	Map<String,Map<Integer,String>> dijkstra(Map<String,List<Node>> adj, int source);
}
