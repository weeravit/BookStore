package com.junebookstore;

import com.junebookstore.helper.JsonWrapper;
import com.junebookstore.model.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private JsonWrapper jsonWrapper = new JsonWrapper();

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            Login user = jsonWrapper.readValue(request.getReader(), Login.class);

            return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        } catch (IOException e) {
            e.printStackTrace();
            throw new InternalAuthenticationServiceException("Failed to parse authentication request body");
        }
    }
}