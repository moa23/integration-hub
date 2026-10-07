package com.example.integrationhub.client;


import com.example.integrationhub.exception.ExternalCustomerNotFoundException;
import com.example.integrationhub.model.ExternalCustomer;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class ExternalCustomerClient {

    private final RestClient restClient;

    public ExternalCustomerClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public ExternalCustomer getCustomer(Long id) {
        return restClient.get()
                .uri("/users/{id}", id)
                .retrieve()
                .onStatus(
                        status -> status.value() == 404,
                        (request, response) -> {
                            throw new ExternalCustomerNotFoundException(
                                    "Customer with id " + id + " was not found"
                            );
                        }
                )
                .body(ExternalCustomer.class);
    }
}