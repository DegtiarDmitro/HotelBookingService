package com.bookingservice.controller;


import com.bookingservice.entity.User;
import com.bookingservice.entity.UserRole;
import com.bookingservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    /**
     * Method creates and returns new user
     * @param user - method expected json object that contain data necessary to convert it to User class
     * @param result - here keeps result of binding and errors of it (if they are)
     * @return -
     * @throws BindException -
     */
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@Valid @RequestBody User user, BindingResult result) throws BindException {

        if(result.hasErrors()){
            throw new BindException(result);
        }
        user.setRole(UserRole.CLIENT);
        userService.add(user);

        return user;
    }

}
