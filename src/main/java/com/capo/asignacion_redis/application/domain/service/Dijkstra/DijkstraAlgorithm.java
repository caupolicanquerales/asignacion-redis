package com.capo.asignacion_redis.application.domain.service.Dijkstra;
import java.util.Map;

import com.capo.asignacion_redis.application.domain.model.GraphModel;

public interface DijkstraAlgorithm {
	Map<String,Map<Integer,String>> dijkstra(GraphModel graphModel, int source);
}
