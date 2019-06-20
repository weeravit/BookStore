package com.junebookstore.repository;

import com.junebookstore.entity.OrderEntity;
import com.junebookstore.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
    List<OrderEntity> findByUser(UserEntity user);
}
