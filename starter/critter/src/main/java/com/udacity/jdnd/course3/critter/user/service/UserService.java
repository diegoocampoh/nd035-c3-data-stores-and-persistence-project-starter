package com.udacity.jdnd.course3.critter.user.service;

import com.udacity.jdnd.course3.critter.user.exceptions.UserNotFoundException;
import com.udacity.jdnd.course3.critter.user.model.Customer;
import com.udacity.jdnd.course3.critter.user.model.Employee;
import com.udacity.jdnd.course3.critter.user.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.user.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public Long saveCustomer(Customer customer){
        return customerRepository.save(customer).getId();
    }

    public List<Customer> getAllCostumers(){
        return customerRepository.findAll();
    }

    public Long saveEmployee(Employee employee){
        return employeeRepository.save(employee).getId();
    }

    public Employee findEmployeeById(Long id){
        return employeeRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

}
