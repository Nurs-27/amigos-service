package com.amigoscode.notification.rabbitmq;

import com.amigoscode.client.fraud.notification.model.NotificationRequest;
import com.amigoscode.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class NotificationConsumer {

    private final NotificationService service;

    @RabbitListener(queues = "${rabbitmq.queues.notification}")
    public void consumer(NotificationRequest request) {
        log.info("Consumed {} from queue", request);

        service.send(request);
    }
}

