package com.example.integrationhub.mapper;

import com.example.integrationhub.model.Customer;
import com.example.integrationhub.model.ExternalCustomer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerMapperTest {

    private final CustomerMapper customerMapper = new CustomerMapper();

    @Test
    void shouldMapExternalCustomerToCustomer() {
        ExternalCustomer externalCustomer = new ExternalCustomer();
        externalCustomer.setId(1L);
        externalCustomer.setName("Leanne Graham");
        externalCustomer.setEmail("Sincere@april.biz");

        Customer customer = customerMapper.toCustomer(externalCustomer);

        assertNull(customer.getId());
        assertEquals("Leanne", customer.getFirstName());
        assertEquals("Graham", customer.getLastName());
        assertEquals("Sincere@april.biz", customer.getEmail());
    }
}