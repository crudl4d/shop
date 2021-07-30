package com.example.shop;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class CustomerServiceTests {

    @InjectMocks
    CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;

    @Before
    public void setUp() {
        Customer alex = new Customer("alex", "password");
        alex.setId(1);
        when(customerRepository.findById(alex.getId())).thenReturn(java.util.Optional.of(alex));
    }
    @Test
    public void whenGetUserItShouldReturnUser() {
        Customer found = customerService.getCustomer(1);
        assertThat(found.getId()).isEqualTo(1);
    }

    @Test
    public void whenDeleteUserItShouldReturnUser(){
        Customer deleted = customerService.deleteCustomer(1);
        assertThat(deleted.getId()).isEqualTo(1);
    }
}
