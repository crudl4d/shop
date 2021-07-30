package com.example.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import javax.validation.Valid;

@RequestMapping("customers")
@Controller
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<Customer>> showUserList() {
        List<Customer> customers= customerService.getCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Customer> addUser(@Valid @RequestBody Customer customer, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Customer _customer = customerService.addCustomer(customer);
        return new ResponseEntity<Customer>(_customer, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Customer> showUser(@PathVariable("id") final int id) {

        Customer customer = customerService.getCustomer(id);
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") final int id,
                                                   @Valid @RequestBody Customer customer, BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Customer customerToEdit = customerService.editCustomer(id ,customer);
        return new ResponseEntity<>(customerToEdit, HttpStatus.OK);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Customer> deleteUser(@PathVariable("id") int id) {
        Customer customer = customerService.deleteCustomer(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
}