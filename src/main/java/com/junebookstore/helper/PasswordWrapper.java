package com.junebookstore.helper;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordWrapper {
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String encodePassword(String plain) {
        return passwordEncoder.encode(plain);
    }

    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }
}
