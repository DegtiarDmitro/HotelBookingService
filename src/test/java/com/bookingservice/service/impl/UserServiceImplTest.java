package com.bookingservice.service.impl;


import com.bookingservice.WebApplication;
import com.bookingservice.dao.UserRepository;
import com.bookingservice.entity.User;
import com.bookingservice.entity.UserRole;
import com.bookingservice.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WebApplication.class )
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    private User user;

    @Before
    public void initUser(){
        user = new User("Test2User", "testuser@gmail.com", "333333", UserRole.CLIENT);
    }


    @Test
    public void addUserTest(){

        when(userRepository.save(any(User.class))).thenReturn(user);
        User savedUser = userServiceImpl.add(user);
        assertEquals(user, savedUser);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void getUserTest(){
        user.setId(1);
        when(userRepository.findOne(anyLong())).thenReturn(user);

        User savedUser = userServiceImpl.get(1);
        assertEquals(1, savedUser.getId());
        verify(userRepository, times(1)).findOne(anyLong());
    }

    @Test
    public void getAllUsersTest(){
        List<User> users = new ArrayList<>();
        users.add(user);
        when(userRepository.findAll()).thenReturn(users);

        List<User> usersSaved = userServiceImpl.getAll();
        assertEquals(1, usersSaved.size());
        verify(userRepository, times(1)).findAll();
    }


    @Test
    public void addGetRemoveUserIntegtationTest(){

        User savedUser = userService.add(user);

        assertTrue(savedUser.getId() != 0);

        User userFromDB = userService.get(savedUser.getId());

        assertEquals(savedUser, userFromDB);

        userService.remove(savedUser.getId());

        assertNull(userService.get(savedUser.getId()));
    }

}
