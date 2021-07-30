package com.example.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CustomerService
{
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public Customer addCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    public List<Customer> getCustomers(){
        return StreamSupport
                .stream(customerRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Customer getCustomer(int id){
        return customerRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Invalid customer ID: " + id));
    }

    public Customer editCustomer(int id, Customer customer){
        Customer customerToEdit = getCustomer(id);
        customerToEdit.setLogin(customer.getLogin());
        customerToEdit.setPassword(customer.getPassword());
        customerToEdit.setEmail(customer.getEmail());
        return customerToEdit;
    }

    public Customer deleteCustomer(int id){
        Customer customer = getCustomer(id);
        customerRepository.delete(customer);
        return customer;
    }
}
