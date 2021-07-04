package com.udacity.jdnd.course3.critter.pet.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Pet not found")
public class PetFoundException extends RuntimeException {
    public PetFoundException() {
    }

    public PetFoundException(String message) {
        super(message);
    }
}
