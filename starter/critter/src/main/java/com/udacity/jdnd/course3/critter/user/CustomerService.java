package com.udacity.jdnd.course3.critter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Long saveCustomer(Customer customer){
        return customerRepository.save(customer).getId();
    }

    public List<Customer> getAllCostumers(){
        return customerRepository.findAll();
    }
}
