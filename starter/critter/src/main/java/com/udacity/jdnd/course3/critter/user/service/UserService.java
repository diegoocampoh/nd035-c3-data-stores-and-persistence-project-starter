package com.udacity.jdnd.course3.critter.user.service;

import com.udacity.jdnd.course3.critter.user.exceptions.UserNotFoundException;
import com.udacity.jdnd.course3.critter.user.model.Customer;
import com.udacity.jdnd.course3.critter.user.model.Employee;
import com.udacity.jdnd.course3.critter.user.model.EmployeeSkill;
import com.udacity.jdnd.course3.critter.user.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.user.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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

    public List<Employee> findEmployeeBySkillAndSchedule(LocalDate date, Set<EmployeeSkill> skills){
        return employeeRepository
                .findAllByDaysAvailableContaining(date.getDayOfWeek()).stream().filter( employee ->
                        employee.getSkills().containsAll(skills)
                ).collect(Collectors.toList());
    }



}
