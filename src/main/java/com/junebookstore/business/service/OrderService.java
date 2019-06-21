package com.junebookstore.business.service;

import com.junebookstore.business.entity.BookEntity;
import com.junebookstore.business.entity.OrderEntity;
import com.junebookstore.business.entity.UserEntity;
import com.junebookstore.common.exception.BookNotFoundException;
import com.junebookstore.common.model.OrderBooks;
import com.junebookstore.common.model.OrderPrice;
import com.junebookstore.business.repository.BookRepository;
import com.junebookstore.business.repository.OrderRepository;
import com.junebookstore.business.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private UserRepository userRepository;
    private BookRepository bookRepository;

    public OrderService(
            @Autowired OrderRepository orderRepository,
            @Autowired UserRepository userRepository,
            @Autowired BookRepository bookRepository
    ) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @Transactional
    public OrderPrice orderBooks(String username, OrderBooks orderBooks) {
        UserEntity userEntity = userRepository.findByUsername(username);

        double sum = orderBooks
                .getOrders()
                .stream()
                .mapToDouble(bookId -> {
                    Optional<BookEntity> bookEntity = bookRepository.findById(bookId);

                    if (!bookEntity.isPresent()) {
                        throw new BookNotFoundException(String.valueOf(bookId));
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
