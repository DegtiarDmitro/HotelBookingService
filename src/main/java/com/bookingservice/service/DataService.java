package com.bookingservice.service;

import com.bookingservice.entity.BaseEntity;
import java.util.List;

public interface DataService<T extends BaseEntity> {

    T add(T t);

    T get(long id);

    List<T> getAll();

    void remove(long id);

    void remove(T t);

    boolean exists(long id);
}
