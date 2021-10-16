package ru.polyan.homework.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.polyan.homework.dto.UserDto;
import ru.polyan.homework.exceptions.ResourceNotFoundException;
import ru.polyan.homework.exceptions.ServiceError;
import ru.polyan.homework.models.User;
import ru.polyan.homework.services.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/users/")
@Slf4j
public class UsersApi {

    private final UserService usersService;

    @PostMapping(value = "/register")
    @ResponseBody
    public ResponseEntity<?> register(@RequestParam Map<String, String> userData){

        Map<String, String> reqFields = new HashMap<>();

        reqFields.put("username", "Login");
        reqFields.put("email", "Email");
        reqFields.put("password", "Password");
        reqFields.put("firstname", "First name");

        String errors = "";
        List<String> fields = new ArrayList<>();

        for(Map.Entry<String, String> reqFld : reqFields.entrySet()){
            String fldVal = userData.get(reqFld.getKey());
            if(fldVal==null){
                fields.add(reqFld.getKey());
                errors = errors.concat("Expected required field '" + reqFld.getKey() + "'; ");
                continue;
            }
            if(fldVal.isBlank()){
                fields.add(reqFld.getKey());
                errors = errors.concat("Required field '" + reqFld.getValue() + "' is empty; ");
            }
        }

        boolean emailIsFree = usersService.findByEmail(userData.get("email")).isEmpty();
        if(!emailIsFree){
            fields.add("email");
            errors = errors.concat("Email is busy; ");
        }
        boolean loginIsFree = usersService.findByUsername(userData.get("username")).isEmpty();
        if(!loginIsFree){
            fields.add("username");
            errors = errors.concat("Login is busy; ");
        }
        if(!errors.isEmpty()){
            ServiceError srvError = new ServiceError(errors);
            srvError.setFieldsWithError(fields);
            return new ResponseEntity(srvError, HttpStatus.BAD_REQUEST);
        }

        usersService.createUser(userData);

        return new ResponseEntity(HttpStatus.OK);

    }

    @GetMapping(value = "/userdata")
    @ResponseBody
    public UserDto getData(Principal principal) {
        String userName = principal.getName();
        return new UserDto(usersService.findByUsername(userName).orElseThrow(()->new ResourceNotFoundException("User with login '" + userName + "' not found!")));
    }

}
