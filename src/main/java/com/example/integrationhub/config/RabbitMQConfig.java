package com.example.integrationhub.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String CUSTOMER_IMPORTED_QUEUE = "customer.imported";

    @Bean
    public Queue customerImportedQueue() {
        return new Queue(CUSTOMER_IMPORTED_QUEUE, true);
    }
}