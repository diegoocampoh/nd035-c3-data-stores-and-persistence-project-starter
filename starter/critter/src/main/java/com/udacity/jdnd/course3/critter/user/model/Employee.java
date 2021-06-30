package com.udacity.jdnd.course3.critter.user.model;

import org.hibernate.annotations.Nationalized;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.DayOfWeek;
import java.util.Collection;

import static java.util.Collections.emptySet;

@Entity
public class Employee {

    @Id
    @GeneratedValue
    private Long id;

    @Nationalized
    private String name;

    @ElementCollection
    private Collection<EmployeeSkill> skills = emptySet();

    @ElementCollection
    private Collection<DayOfWeek> daysAvailable = emptySet();

    public Employee(String name, Collection<EmployeeSkill> skills, Collection<DayOfWeek> daysAvailable) {
        this.name = name;
        this.skills = skills;
        this.daysAvailable = daysAvailable;
    }

    public Employee() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<EmployeeSkill> getSkills() {
        return skills;
    }

    public void setSkills(Collection<EmployeeSkill> skills) {
        this.skills = skills;
    }

    public Collection<DayOfWeek> getDaysAvailable() {
        return daysAvailable;
    }

    public void setDaysAvailable(Collection<DayOfWeek> daysAvailable) {
        this.daysAvailable = daysAvailable;
    }
}
