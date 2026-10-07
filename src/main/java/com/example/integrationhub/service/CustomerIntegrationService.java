package com.example.integrationhub.service;
import com.example.integrationhub.messaging.CustomerEventPublisher;
import com.example.integrationhub.client.ExternalCustomerClient;
import com.example.integrationhub.mapper.CustomerMapper;
import com.example.integrationhub.model.Customer;
import com.example.integrationhub.model.ExternalCustomer;
import com.example.integrationhub.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerIntegrationService {

    private final ExternalCustomerClient externalCustomerClient;
    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;
    private final CustomerEventPublisher customerEventPublisher;

    public CustomerIntegrationService(
            ExternalCustomerClient externalCustomerClient,
            CustomerMapper customerMapper,
            CustomerRepository customerRepository,
            CustomerEventPublisher customerEventPublisher) {

        this.externalCustomerClient = externalCustomerClient;
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
        this.customerEventPublisher = customerEventPublisher;
    }
    public Customer importCustomer(Long externalCustomerId) {

        ExternalCustomer externalCustomer =
                externalCustomerClient.getCustomer(externalCustomerId);

        Customer customer =
                customerMapper.toCustomer(externalCustomer);

        Customer savedCustomer =
                customerRepository.save(customer);

        customerEventPublisher.publishCustomerImported(savedCustomer.getId());

        return savedCustomer;
    }
}