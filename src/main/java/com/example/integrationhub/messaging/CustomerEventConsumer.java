package com.example.integrationhub.messaging;

import com.example.integrationhub.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class CustomerEventConsumer {

    @RabbitListener(queues = RabbitMQConfig.CUSTOMER_IMPORTED_QUEUE)
    public void handleCustomerImported(Long customerId) {
        System.out.println(
                "Received customer.imported event for customer: " + customerId
        );
    }
}