package com.bookingservice.dao;

import com.bookingservice.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataRepository<T extends BaseEntity> extends JpaRepository<T, Long> {
}
