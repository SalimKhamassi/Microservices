package com.example.kafka;

import com.example.dto.Event;
import com.example.dto.UserDto;
import com.example.shared.EventType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {
    private final KafkaTemplate<String, Event> kafkaTemplate;
    private static final String TOPIC = "user-event-topic";

    public KafkaProducer(KafkaTemplate<String, Event> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendUserEvent(EventType eventType, UserDto userDto) {
        Event event = new Event(eventType, userDto);
        kafkaTemplate.send(TOPIC, event);
    }
}