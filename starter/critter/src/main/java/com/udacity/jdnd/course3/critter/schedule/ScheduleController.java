package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.service.PetService;
import com.udacity.jdnd.course3.critter.schedule.model.Schedule;
import com.udacity.jdnd.course3.critter.schedule.service.ScheduleService;
import com.udacity.jdnd.course3.critter.user.service.UserService;
import lombok.val;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private PetService petService;

    @Autowired
    private UserService userService;

    @Autowired
    private ScheduleService scheduleService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        val employees = scheduleDTO.getEmployeeIds().stream()
                .map(userService::findEmployeeById).collect(Collectors.toList());
        val pets = scheduleDTO.getPetIds().stream()
                .map(petService::getPetById).collect(Collectors.toList());
        val id = scheduleService.saveSchedule(
                new Schedule(
                        employees,
                        pets,
                        scheduleDTO.getActivities(),
                        scheduleDTO.getDate()
                )
        );
        scheduleDTO.setId(id);
        ScheduleDTO response = new ScheduleDTO();
        BeanUtils.copyProperties(scheduleDTO, response);
        return response;
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        return scheduleService
                .getAllSchedules()
                .stream().map(ScheduleDTO::fromSchedule)
                .collect(Collectors.toList());
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        return scheduleService.getSchedulesForPetId(petId)
                .stream().map(ScheduleDTO::fromSchedule)
                .collect(Collectors.toList());
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        return scheduleService.getSchedulesForEmployeeId(employeeId)
                .stream().map(ScheduleDTO::fromSchedule)
                .collect(Collectors.toList());
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        return scheduleService.getSchedulesForCustomerId(customerId)
                .stream().map(ScheduleDTO::fromSchedule)
                .collect(Collectors.toList());
    }
}
