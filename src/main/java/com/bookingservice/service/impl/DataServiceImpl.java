package com.bookingservice.service.impl;


import com.bookingservice.dao.DataRepository;
import com.bookingservice.entity.BaseEntity;
import com.bookingservice.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataServiceImpl<T extends BaseEntity> implements DataService<T> {


    private final DataRepository<T> dataRepository;

    @Autowired
    public DataServiceImpl(DataRepository<T> dataRepository) {
        this.dataRepository = dataRepository;
    }


    @Override
    public T add(T t) {
        return dataRepository.save(t);
    }

    @Override
    public T get(long id){
        return dataRepository.findOne(id);
    }

    @Override
    public List<T> getAll() {
        return dataRepository.findAll();
    }

    @Override
    public void remove(long id) {
        dataRepository.delete(id);
    }

    @Override
    public void remove(T t) {
        dataRepository.delete(t);
    }

    @Override
    public boolean exists(long id) {
        return dataRepository.exists(id);
    }
}
