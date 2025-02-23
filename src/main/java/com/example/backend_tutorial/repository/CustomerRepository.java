package com.example.backend_tutorial.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backend_tutorial.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    public Optional<Customer> findCustomerById(Long id);
    
}
