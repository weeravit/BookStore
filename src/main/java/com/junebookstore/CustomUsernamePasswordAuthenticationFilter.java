package com.junebookstore;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.junebookstore.model.Login;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Login user = objectMapper.readValue(request.getReader(), Login.class);

            return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        } catch (IOException e) {
            e.printStackTrace();
            throw new InternalAuthenticationServiceException("Failed to parse authentication request body");
        }
    }
}