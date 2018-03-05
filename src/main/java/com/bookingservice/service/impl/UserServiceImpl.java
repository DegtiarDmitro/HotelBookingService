package com.bookingservice.service.impl;

import com.bookingservice.dao.DataRepository;
import com.bookingservice.entity.User;
import com.bookingservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserServiceImpl extends DataServiceImpl<User> implements UserService {


    @Autowired
    public UserServiceImpl(DataRepository<User> dataRepository) {
        super(dataRepository);
    }

}
