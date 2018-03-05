package com.bookingservice.controller;


import com.bookingservice.WebApplication;
import com.bookingservice.entity.User;
import com.bookingservice.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;

import javax.validation.ValidationException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WebApplication.class )
public class UserControllerTest {


    @Autowired
    UserController userController;

    @Mock
    private UserService userService;

    @Mock
    private BindingResult bindingResult;

    private User user;

    @Before
    public void initUser(){
        user = new User();
        user.setUsername("TestUser");
        user.setEmail("testuser@gmail.com");
        user.setPassword("111111");
    }

    @Test(expected = ValidationException.class)
    public void createUserExpectedExceptionTest() throws BindException {

        user.setPassword("1111");
        when(userService.add(any(User.class))).thenReturn(user);
        User savedUser = userController.createUser(user, bindingResult);
        assertEquals(user, savedUser);
    }

    @Test
    public void createUserTest() throws BindException {

        when(userService.add(any(User.class))).thenReturn(user);
        User savedUser = userController.createUser(user, bindingResult);
        assertEquals(user, savedUser);
    }

}
