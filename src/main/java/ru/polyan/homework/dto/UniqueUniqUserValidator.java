package ru.polyan.homework.dto;

import org.springframework.beans.factory.annotation.Autowired;
import ru.polyan.homework.services.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUniqUserValidator implements ConstraintValidator<UniqUserName, String> {


    @Autowired
    private UserService usersService;

    @Override
    public void initialize(UniqUserName uniqUserName) {

    }

    @Override
    public boolean isValid(String checkValue, ConstraintValidatorContext ctx) {
        if (!usersService.findByUsername(checkValue).isEmpty()){
            return false;
        }
        return true;
    }

}
