package com.example.integrationhub.controller;

import com.example.integrationhub.model.Customer;
import com.example.integrationhub.model.ExternalCustomer;
import com.example.integrationhub.client.ExternalCustomerClient;
import com.example.integrationhub.service.CustomerIntegrationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/integrations")
public class IntegrationController {

    private final ExternalCustomerClient externalCustomerClient;
    private final CustomerIntegrationService customerIntegrationService;

    public IntegrationController(
            ExternalCustomerClient externalCustomerClient,
            CustomerIntegrationService customerIntegrationService) {
        this.externalCustomerClient = externalCustomerClient;
        this.customerIntegrationService = customerIntegrationService;
    }

    @GetMapping("/customers/{id}")
    public ExternalCustomer getExternalCustomer(@PathVariable Long id) {
        return externalCustomerClient.getCustomer(id);
    }

    @PostMapping("/customers/import/{id}")
    public Customer importCustomer(@PathVariable Long id) {
        return customerIntegrationService.importCustomer(id);
    }
}