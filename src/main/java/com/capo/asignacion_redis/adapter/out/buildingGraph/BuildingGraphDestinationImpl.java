package com.capo.asignacion_redis.adapter.out.buildingGraph;

import org.springframework.stereotype.Service;

import com.capo.asignacion_redis.adapter.enums.RedisEnum;
import com.capo.asignacion_redis.adapter.out.persistence.redisPetition.BasicPetitionToRedis;
import com.capo.asignacion_redis.application.domain.service.buildingGraph.BuildingGraphImpl;
import com.capo.asignacion_redis.application.port.in.CostsAndPointsMaps;
import com.capo.asignacion_redis.application.port.out.BuildingGraph;

import reactor.core.publisher.Mono;

@Service
public class BuildingGraphDestinationImpl implements BuildingGraphDestination{
	
	private final CostsAndPointsMaps costsAndPointsMaps;
	private final BasicPetitionToRedis petitionToRedis;
	
	
	public BuildingGraphDestinationImpl(CostsAndPointsMaps costsAndPointsMaps,
			BasicPetitionToRedis petitionToRedis) {
		this.costsAndPointsMaps=costsAndPointsMaps;
		this.petitionToRedis= petitionToRedis;
	}
	
	@Override
	public Mono<String> buildingGraph(){
		BuildingGraph buildingGraph = new BuildingGraphImpl(costsAndPointsMaps);
		return buildingGraph.buildingGraph()
				.map(grahp->petitionToRedis.saveReactiveJsonBucket(RedisEnum.GRAPH.value,grahp));
	} 
}
