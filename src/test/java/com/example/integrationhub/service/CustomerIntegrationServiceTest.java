package com.example.integrationhub.service;

import com.example.integrationhub.client.ExternalCustomerClient;
import com.example.integrationhub.mapper.CustomerMapper;
import com.example.integrationhub.model.Customer;
import com.example.integrationhub.model.ExternalCustomer;
import com.example.integrationhub.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import com.example.integrationhub.messaging.CustomerEventPublisher;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerIntegrationServiceTest {

    @Mock
    private ExternalCustomerClient externalCustomerClient;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;
    @Mock
    private CustomerEventPublisher customerEventPublisher;

    @InjectMocks
    private CustomerIntegrationService customerIntegrationService;
    @Test
    void shouldImportExternalCustomer() {

        ExternalCustomer externalCustomer = new ExternalCustomer();
        externalCustomer.setId(1L);
        externalCustomer.setName("Leanne Graham");
        externalCustomer.setEmail("Sincere@april.biz");

        Customer savedCustomer = new Customer(
                10L,
                "Leanne",
                "Graham",
                "Sincere@april.biz"
        );

        when(externalCustomerClient.getCustomer(1L))
                .thenReturn(externalCustomer);
        when(customerMapper.toCustomer(externalCustomer))
                .thenReturn(new Customer(
                        null,
                        "Leanne",
                        "Graham",
                        "Sincere@april.biz"
                ));
        when(customerRepository.save(any(Customer.class)))
                .thenReturn(savedCustomer);

        Customer result =
                customerIntegrationService.importCustomer(1L);

        assertEquals(10L, result.getId());
        assertEquals("Leanne", result.getFirstName());
        assertEquals("Graham", result.getLastName());
        assertEquals("Sincere@april.biz", result.getEmail());

        verify(externalCustomerClient).getCustomer(1L);
        verify(customerRepository).save(any(Customer.class));
        verify(customerEventPublisher).publishCustomerImported(10L);
    }
}