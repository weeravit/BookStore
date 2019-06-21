package com.junebookstore.service;

import com.junebookstore.entity.UserEntity;
import com.junebookstore.helper.SecureHelper;
import com.junebookstore.model.Register;
import com.junebookstore.model.UserInformation;
import com.junebookstore.model.UserPrincipal;
import com.junebookstore.repository.OrderRepository;
import com.junebookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    private UserRepository userRepository;
    private OrderRepository orderRepository;
    private SecureHelper secureHelper;

    public UserService(
            @Autowired UserRepository userRepository,
            @Autowired OrderRepository orderRepository,
            @Autowired SecureHelper secureHelper
    ) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.secureHelper = secureHelper;
    }

    public int register(Register data) {
        UserEntity entity = new UserEntity(
                data.getUsername(),
                secureHelper.encodePassword(data.getPassword()),
                data.getDateOfBirth(),
                data.getName(),
                data.getSurname()
        );

        UserEntity saved = userRepository.save(entity);

        return saved.getId();
    }

    public UserInformation getInformation(String username) {
        UserEntity user = userRepository.findByUsername(username);
        List<Integer> books = orderRepository.findByUser(user)
                .stream()
                .map(order -> order.getBook().getId())
                .collect(Collectors.toList());

        return new UserInformation(
                user.getName(),
                user.getSurname(),
                user.getDateOfBirth(),
                books
        );
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity entity = userRepository.findByUsername(username);

        if (entity == null) {
            throw new UsernameNotFoundException(username);
        }

        return new UserPrincipal(entity);
    }
}
