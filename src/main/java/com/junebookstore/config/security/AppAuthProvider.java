package com.junebookstore.config.security;

import com.junebookstore.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AppAuthProvider {
    @Bean
    public static DaoAuthenticationProvider getProvider(UserService service, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(service);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }
}
