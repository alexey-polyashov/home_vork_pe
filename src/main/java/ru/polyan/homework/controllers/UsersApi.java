package ru.polyan.homework.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.polyan.homework.dto.NewUserDto;
import ru.polyan.homework.dto.UserDto;
import ru.polyan.homework.exceptions.ResourceNotFoundException;
import ru.polyan.homework.models.User;
import ru.polyan.homework.services.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/users/")
@Slf4j
public class UsersApi {

    private final UserService usersService;
    private final ModelMapper modelMapper;

    @PostMapping(value = "/register")
    @ResponseBody
    public ResponseEntity<?> register(@Valid @RequestBody NewUserDto userData){

        User user = modelMapper.map(userData, User.class);
        usersService.createUser(user);
        return new ResponseEntity(HttpStatus.OK);

    }

    @GetMapping(value = "/userdata")
    @ResponseBody
    public UserDto getData(Principal principal) {
        String userName = principal.getName();
        User user = usersService.findByUsername(userName).orElseThrow(()->new ResourceNotFoundException("User with login '" + userName + "' not found!"));
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
        //return new UserDto(usersService.findByUsername(userName).orElseThrow(()->new ResourceNotFoundException("User with login '" + userName + "' not found!")));
    }

}
