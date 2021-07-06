package com.udacity.jdnd.course3.critter.schedule.model;

import com.udacity.jdnd.course3.critter.pet.model.Pet;
import com.udacity.jdnd.course3.critter.user.model.Employee;
import com.udacity.jdnd.course3.critter.user.model.EmployeeSkill;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.Collections.emptySet;

@Entity
public class Schedule {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Employee> employees = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Pet> pets = new ArrayList<>();

    @ElementCollection
    private Collection<EmployeeSkill> skills = emptySet();

    private LocalDate date;

    public Schedule() {
    }

    public Schedule(List<Employee> employees, List<Pet> pets, Collection<EmployeeSkill> skills, LocalDate date) {
        this.employees = employees;
        this.pets = pets;
        this.skills = skills;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public Collection<EmployeeSkill> getSkills() {
        return skills;
    }

    public void setSkills(Collection<EmployeeSkill> skills) {
        this.skills = skills;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
