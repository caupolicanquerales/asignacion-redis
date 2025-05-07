package com.capo.asignacion_redis.application.domain.service.buildingGraph;

import java.util.List;
import java.util.Map;

import com.capo.asignacion_redis.application.domain.model.GraphModel;
import com.capo.asignacion_redis.application.domain.model.Node;
import com.capo.asignacion_redis.application.domain.model.WeightedGraphModel;
import com.capo.asignacion_redis.application.port.in.CostsAndPointsMaps;
import com.capo.asignacion_redis.application.port.out.BuildingGraph;

import reactor.core.publisher.Mono;


public class BuildingGraphImpl implements BuildingGraph {
	
	private CostsAndPointsMaps costsAndPointsMaps;
	
	public BuildingGraphImpl(CostsAndPointsMaps costsAndPointsMaps) {
		this.costsAndPointsMaps=costsAndPointsMaps;
	}
	
	@Override
	public Mono<GraphModel> buildingGraph() {
		Mono<Map<String,String>> mapStores= getMapStores();
		Mono<Map<String,String>> mapCost= getMapCost();
		return Mono.zip(mapStores, mapCost).map(tuple->processOfBuildingGraph(tuple.getT1(),tuple.getT2()));
	}
	
	private Mono<Map<String,String>> getMapCost() {
		return this.costsAndPointsMaps.getMapforDestinationAndCost()
		.collectMap(Map.Entry::getKey,Map.Entry::getValue);
	}
	
	private Mono<Map<String,String>> getMapStores() {
		return this.costsAndPointsMaps.getMapforPointsOfSale()
			.collectMap(Map.Entry::getKey,Map.Entry::getValue);
	}
	
	private GraphModel processOfBuildingGraph(Map<String,String> mapStores,Map<String,String> mapCost){
		WeightedGraphModel weightedGraphObject= getWeightedGraph(mapStores);
		return fillWeightedGraph(mapCost, weightedGraphObject);
	}
	
	private WeightedGraphModel getWeightedGraph(Map<String,String> mapStores) {
		WeightedGraphModel objectGraph= new WeightedGraphModel();
		for(int i=0;i<mapStores.size();i++) {
			objectGraph.createVertex(String.valueOf(i+1));
		}
		return objectGraph;
	}
	
	private GraphModel fillWeightedGraph(Map<String,String> mapCost, WeightedGraphModel objectGraph) {
		for(Map.Entry<String,String> entry:mapCost.entrySet()) {
			String[] stores= entry.getKey().split(",");
			objectGraph.addEdge(stores[0],Integer.valueOf(stores[1]),Integer.valueOf(entry.getValue()));
		}
		return getGraphObject(objectGraph.getGraph());
	}
	
	private GraphModel getGraphObject(Map<String,List<Node>> graphMap) {
		GraphModel graphObject = new GraphModel();
		graphObject.setGraphObject(graphMap);
		return graphObject; 
	} 	
}
