package com.junebookstore.service;

import com.junebookstore.entity.BookEntity;
import com.junebookstore.entity.OrderEntity;
import com.junebookstore.entity.UserEntity;
import com.junebookstore.model.OrderBooks;
import com.junebookstore.model.OrderPrice;
import com.junebookstore.repository.BookRepository;
import com.junebookstore.repository.OrderRepository;
import com.junebookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BookRepository bookRepository;

    public OrderPrice orderBooks(String username, OrderBooks orderBooks) {
        UserEntity userEntity = userRepository.findByUsername(username);

        double sum = orderBooks
                .getOrders()
                .stream()
                .mapToDouble(bookId -> {
                    Optional<BookEntity> bookEntity = bookRepository.findById(bookId);

                    if (!bookEntity.isPresent()) {
                        return 0;
                    }

                    OrderEntity orderEntity = new OrderEntity(userEntity, bookEntity.get());
                    orderRepository.save(orderEntity);

                    return bookEntity.get().getPrice();
                }).sum();

        return new OrderPrice(sum);
    }

    public void deleteOrderHistory(String username) {
        UserEntity userEntity = userRepository.findByUsername(username);
        List<OrderEntity> orderEntities = orderRepository.findByUser(userEntity);

        orderRepository.deleteAll(orderEntities);
    }
}
