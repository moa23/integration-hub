package com.example.integrationhub.messaging;

import com.example.integrationhub.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class CustomerEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public CustomerEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishCustomerImported(Long customerId) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.CUSTOMER_IMPORTED_QUEUE,
                customerId
        );
    }
}