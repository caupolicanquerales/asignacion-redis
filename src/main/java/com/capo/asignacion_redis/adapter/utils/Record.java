package com.capo.asignacion_redis.adapter.utils;

import reactor.kafka.receiver.ReceiverOffset;

public record Record<T>(String key,
                        T message,
                        ReceiverOffset acknowledgement) {
}
