package com.example.integrationhub.mapper;

import com.example.integrationhub.model.Customer;
import com.example.integrationhub.model.ExternalCustomer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer toCustomer(ExternalCustomer externalCustomer) {
        String[] nameParts = externalCustomer.getName().split(" ", 2);

        String firstName = nameParts[0];
        String lastName = nameParts.length > 1 ? nameParts[1] : "";

        return new Customer(
                null,
                firstName,
                lastName,
                externalCustomer.getEmail()
        );
    }
}