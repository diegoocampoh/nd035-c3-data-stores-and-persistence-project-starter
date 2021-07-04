package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.pet.service.PetService;
import com.udacity.jdnd.course3.critter.user.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetService petService;

    @Autowired
    private UserService userService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Long id = petService.savePet(new Pet(
            petDTO.getType(),
                petDTO.getName(),
                petDTO.getBirthDate(),
                petDTO.getNotes(),
                userService.findCustomerById(petDTO.getOwnerId())
        ));
        petDTO.setId(id);
        PetDTO response = new PetDTO();
        BeanUtils.copyProperties(petDTO, response);
        return response;
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        Pet pet = petService.getPetById(petId);
        return PetDTO.fromPet(pet);

    }

    @GetMapping
    public List<PetDTO> getPets(){
        return petService
                .getAllPets()
                .stream()
                .map(PetDTO::fromPet)
                .collect(Collectors.toList());
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        return petService
                .getPetByOwnerId(ownerId)
                .stream()
                .map(PetDTO::fromPet)
                .collect(Collectors.toList());
    }
}
