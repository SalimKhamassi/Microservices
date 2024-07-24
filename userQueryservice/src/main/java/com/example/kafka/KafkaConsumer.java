package com.example.kafka;


import com.example.dto.Event;
import com.example.events.UserEventHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumer {
    private final UserEventHandler userEventHandler;
    private static final String TOPIC = "user-event-topic";

    @KafkaListener(topics = TOPIC, groupId = "user-query-service-group")
    public void consume(Event event) {
        log.info("Consumed Event of type: {} Data: {}", event.type(), event.userDto());

        switch (event.type()) {
            case CREATED_USER_EVENT:
                userEventHandler.handleUserCreatedEvent(event.userDto());
                break;
            case UPDATED_USER_EVENT:
                userEventHandler.handleUserUpdatedEvent(event.userDto());
                break;
            case DELETED_USER_EVENT:
                userEventHandler.handleUserDeletedEvent(event.userDto().id());
                break;
            default:
                log.info("Event ignored");
                break;
        }
    }
}
