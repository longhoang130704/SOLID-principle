package com.example.backend_tutorial.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.backend_tutorial.dto.ApiRequest.CustomerApiRequest;
import com.example.backend_tutorial.dto.ApiResponse.CustomerApiResponse;
import com.example.backend_tutorial.model.Customer;
import com.example.backend_tutorial.repository.CustomerRepository;

@Service
public class CustomerService {
    
    @Autowired
    private CustomerRepository customerRepository;

    public CustomerApiResponse createCustomer(CustomerApiRequest customerApiRequest) {
        try {
            Customer newcustomer = convertToCustom(customerApiRequest);

            Customer savedCustomer = customerRepository.save(newcustomer);

            CustomerApiResponse customerApiResponse = convertToApiResponse(savedCustomer);
            return customerApiResponse;

        } catch (DataIntegrityViolationException e) {
            return new CustomerApiResponse(400, "Error: Duplicate email or CCCD ", null, null, null, null, null, null);
        } catch (Exception e) {
            return new CustomerApiResponse(500, "Internal Server Error ", null, null, null, null, null, null);
        }
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public CustomerApiResponse findCustomerById(Long id) {
        try {
            return customerRepository.findById(id)
                .map(this::convertToApiResponse)
                .orElseThrow(() -> new RuntimeException("Not Found Customer ID: " + id));
        } catch (Exception e) {
            return new CustomerApiResponse(500, "Internal Server Error " + e.getMessage(), null, null, null, null, null, null);
        }
    }

    public CustomerApiResponse updateCustomer(Long id, CustomerApiRequest updateRequest) {
        try {
            return customerRepository.findById(id)
                .map(existingCustomer  -> {
                    
                    Customer customer = convertToCustom(updateRequest);

                    try {
                        Customer updatedCustomer = customerRepository.save(customer);
                        return convertToApiResponse(updatedCustomer);
                    } catch (DataIntegrityViolationException e) {
                        throw new RuntimeException("Email hoặc CCCD đã tồn tại! ");
                    }
                })
                .orElseThrow(() -> new RuntimeException("Not Found Customer ID: " + id));
        
            } catch (Exception e) {
            return new CustomerApiResponse(500, "Internal Server Error " + e.getMessage(), null, null, null, null, null, null);
        }
    }

    private Customer convertToCustom(CustomerApiRequest customerApiRequest) {
        Customer customer = new Customer();
        customer.setAddress(customerApiRequest.getAddress());
        customer.setCccd(customerApiRequest.getCccd());
        customer.setEmail(customerApiRequest.getEmail());
        customer.setName(customerApiRequest.getName());
        customer.setPhone(customerApiRequest.getPhone());

        return customer;
    }

    private CustomerApiResponse convertToApiResponse(Customer customer) {
        return new CustomerApiResponse(
            200,
            "Success",
            customer.getId(),
            customer.getCccd(),
            customer.getName(),
            customer.getEmail(),
            customer.getPhone(),
            customer.getAddress()
        );
    }

}
