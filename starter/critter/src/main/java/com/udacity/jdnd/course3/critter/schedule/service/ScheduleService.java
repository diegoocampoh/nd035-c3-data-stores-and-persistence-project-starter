package com.udacity.jdnd.course3.critter.schedule.service;

import com.udacity.jdnd.course3.critter.schedule.model.Schedule;
import com.udacity.jdnd.course3.critter.schedule.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    public Long saveSchedule(Schedule schedule){
        return scheduleRepository.save(schedule).getId();
    }

    public List<Schedule> getAllSchedules(){
        return scheduleRepository.findAll();
    }

    public List<Schedule> getSchedulesForPetId(Long id){
        return scheduleRepository.findAllByPetId(id);
    }

    public List<Schedule> getSchedulesForEmployeeId(Long id){
        return scheduleRepository.findAllByEmployee(id);
    }

    public List<Schedule> getSchedulesForCustomerId(Long id){
        return scheduleRepository.findAllByCustomer(id);
    }

}
