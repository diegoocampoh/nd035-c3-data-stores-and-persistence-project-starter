package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.model.Pet;
import com.udacity.jdnd.course3.critter.schedule.model.Schedule;
import com.udacity.jdnd.course3.critter.user.model.Employee;
import com.udacity.jdnd.course3.critter.user.model.EmployeeSkill;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Represents the form that schedule request and response data takes. Does not map
 * to the database directly.
 */
public class ScheduleDTO {
    private long id;
    private List<Long> employeeIds;
    private List<Long> petIds;
    private LocalDate date;
    private Set<EmployeeSkill> activities;

    public static ScheduleDTO fromSchedule(Schedule schedule){
        return new ScheduleDTO(
                schedule.getId(),
                schedule.getEmployees().stream().map(Employee::getId).collect(Collectors.toList()),
                schedule.getPets().stream().map(Pet::getId).collect(Collectors.toList()),
                schedule.getDate(),
                new HashSet<>(schedule.getSkills())
        );
    }

    public ScheduleDTO() {
    }

    public ScheduleDTO(long id, List<Long> employeeIds, List<Long> petIds, LocalDate date, Set<EmployeeSkill> activities) {
        this.id = id;
        this.employeeIds = employeeIds;
        this.petIds = petIds;
        this.date = date;
        this.activities = activities;
    }

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public List<Long> getEmployeeIds() {
        return employeeIds;
    }

    public void setEmployeeIds(List<Long> employeeIds) {
        this.employeeIds = employeeIds;
    }

    public List<Long> getPetIds() {
        return petIds;
    }

    public void setPetIds(List<Long> petIds) {
        this.petIds = petIds;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<EmployeeSkill> getActivities() {
        return activities;
    }

    public void setActivities(Set<EmployeeSkill> activities) {
        this.activities = activities;
    }
}
