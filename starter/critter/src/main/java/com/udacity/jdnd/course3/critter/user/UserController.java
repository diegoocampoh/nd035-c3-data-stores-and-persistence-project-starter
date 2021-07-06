package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.user.dto.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.dto.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.dto.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.user.model.Customer;
import com.udacity.jdnd.course3.critter.user.model.Employee;
import com.udacity.jdnd.course3.critter.user.service.UserService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        val id = userService.saveCustomer(
                new Customer(customerDTO.getName(),
                        customerDTO.getPhoneNumber(),
                        customerDTO.getNotes()
                )
        );
        customerDTO.setId(id);
        return customerDTO;
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        return userService.getAllCostumers()
                .stream()
                .map(CustomerDTO::fromCustomer)
                .collect(Collectors.toList());
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        return CustomerDTO.fromCustomer(userService.findCustomerByPetId(petId));
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        val id = userService.saveEmployee(
                new Employee(
                        employeeDTO.getName(),
                        employeeDTO.getSkills(),
                        employeeDTO.getDaysAvailable()
                )
        );
        employeeDTO.setId(id);
        return employeeDTO;
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        val employee = userService.findEmployeeById(employeeId);
        return new EmployeeDTO(
                employee.getId(),
                employee.getName(),
                new HashSet<>(employee.getSkills()),
                new HashSet<>(employee.getDaysAvailable())
        );
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        val employee = userService.findEmployeeById(employeeId);
        employee.setDaysAvailable(daysAvailable);
        userService.saveEmployee(employee);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        return userService.findEmployeeBySkillAndSchedule(employeeDTO.getDate(), employeeDTO.getSkills())
                .stream()
                .map( dto -> new EmployeeDTO(
                        dto.getId(),
                        dto.getName(),
                        new HashSet<>(dto.getSkills()),
                        new HashSet<>(dto.getDaysAvailable())
                ))
                .collect(Collectors.toList());
    }

}
