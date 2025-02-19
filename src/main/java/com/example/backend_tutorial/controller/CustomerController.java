package com.example.backend_tutorial.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend_tutorial.dto.ApiRequest.CustomerApiRequest;
import com.example.backend_tutorial.dto.ApiResponse.CustomerApiResponse;
import com.example.backend_tutorial.model.Customer;
import com.example.backend_tutorial.service.CustomerService;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/")
    public CustomerApiResponse createCustomer(@RequestBody CustomerApiRequest customerApiRequest) {
        return customerService.createCustomer(customerApiRequest);
    }

    @GetMapping("/{customerId}")
    public CustomerApiResponse findCustomerById(@PathVariable Long customerId) {
        return customerService.findCustomerById(customerId);
    }

    @GetMapping("/")
    public List<Customer> getAllCustomer() {
        return customerService.getAllCustomers();
    }

    @PutMapping("/{customerId}")
    public CustomerApiResponse updateCustomer(@PathVariable Long customerId, @RequestBody CustomerApiRequest customerApiRequest) {
        return customerService.updateCustomer(customerId, customerApiRequest);
    }

}
