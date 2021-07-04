package com.udacity.jdnd.course3.critter.pet.service;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.exceptions.PetFoundException;
import com.udacity.jdnd.course3.critter.pet.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    public Long savePet(Pet pet){
        pet.getOwner().getPets().add(pet);
        return petRepository.save(pet).getId();
    }

    public Pet getPetById(Long id){
        return petRepository.findById(id).orElseThrow(PetFoundException::new);
    }

    public List<Pet> getPetByOwnerId(Long ownerId){
        return petRepository.findAllByOwnerId(ownerId);
    }
    public List<Pet> getAllPets(){
        return petRepository.findAll();
    }

}
