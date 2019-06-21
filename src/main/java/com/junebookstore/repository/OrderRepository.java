package com.junebookstore.repository;

import com.junebookstore.entity.OrderEntity;
import com.junebookstore.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
    List<OrderEntity> findByUser(UserEntity user);
}
