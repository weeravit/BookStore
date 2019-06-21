package com.junebookstore.business.service;

import com.junebookstore.business.entity.UserEntity;
import com.junebookstore.common.wrapper.PasswordWrapper;
import com.junebookstore.common.model.Register;
import com.junebookstore.common.model.UserInformation;
import com.junebookstore.common.model.UserPrincipal;
import com.junebookstore.business.repository.OrderRepository;
import com.junebookstore.business.repository.UserRepository;
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
    private PasswordWrapper passwordWrapper;

    public UserService(
            @Autowired UserRepository userRepository,
            @Autowired OrderRepository orderRepository,
            @Autowired PasswordWrapper passwordWrapper
    ) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.passwordWrapper = passwordWrapper;
    }

    public int register(Register data) {
        UserEntity entity = new UserEntity(
                data.getUsername(),
                passwordWrapper.encodePassword(data.getPassword()),
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
