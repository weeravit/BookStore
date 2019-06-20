package com.junebookstore.service;

import com.junebookstore.entity.UserEntity;
import com.junebookstore.model.Register;
import com.junebookstore.model.UserPrincipal;
import com.junebookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository repository;

    public int register(Register data) {
        UserEntity entity = new UserEntity(
                data.getUsername(),
                passwordEncoder().encode(data.getPassword()),
                data.getDateOfBirth(),
                data.getName(),
                data.getSurname()
        );

        UserEntity saved = repository.save(entity);

        return saved.getId();
    }

    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity entity = repository.findByUsername(username);

        if (entity == null) {
            throw new UsernameNotFoundException(username);
        }

        return new UserPrincipal(entity);
    }
}
