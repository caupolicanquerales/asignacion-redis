package com.capo.asignacion_redis.adapter.configuration;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import com.capo.adapter.kafkaEvents.RedisUpdateDestinationEvent;
import com.capo.asignacion_redis.adapter.out.emitEvents.EventInUse;

import reactor.core.publisher.Flux;

@Configuration
public class PublishingUpdateDestinationConfig {
	
	private final EventInUse<RedisUpdateDestinationEvent> eventPublisher;
	
	private static final String DESTINATION_HEADER = "spring.cloud.stream.sendto.destination";
    private static final String EVENTS_CHANNEL = "event-update-destination";
    
	@Autowired
	public PublishingUpdateDestinationConfig(EventInUse<RedisUpdateDestinationEvent> eventPublisher) {
		this.eventPublisher=eventPublisher;
	}
	
	@Bean
    public Supplier<Flux<Message<RedisUpdateDestinationEvent>>> publishingUpdateDestinationEvent() {
        return () -> this.eventPublisher.publish()
                                        .map(this::toMessage);
    }
	
	private Message<RedisUpdateDestinationEvent> toMessage(RedisUpdateDestinationEvent event) {
        return MessageBuilder.withPayload(event)
                             .setHeader(KafkaHeaders.KEY, "16")
                             .setHeader(DESTINATION_HEADER, EVENTS_CHANNEL)
                             .build();
    }
}
