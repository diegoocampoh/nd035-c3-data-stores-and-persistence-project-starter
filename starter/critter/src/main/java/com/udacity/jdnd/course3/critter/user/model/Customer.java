package com.udacity.jdnd.course3.critter.user.model;

import com.udacity.jdnd.course3.critter.pet.Pet;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;

@Entity
public class Customer extends User{

    @Column(length = 50)
    private String phoneNumber;

    @Column(length = 500)
    private String notes;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Pet> pets = new ArrayList<>();

    public Customer() {
    }

    public Customer(String name, String phoneNumber, String notes) {
        super(name);
        this.phoneNumber = phoneNumber;
        this.notes = notes;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }
}
