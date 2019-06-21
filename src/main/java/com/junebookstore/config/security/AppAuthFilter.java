package com.junebookstore.config.security;

import com.junebookstore.common.wrapper.JsonWrapper;
import com.junebookstore.model.Login;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AppAuthFilter extends UsernamePasswordAuthenticationFilter {
    private JsonWrapper jsonWrapper = new JsonWrapper();

    @Bean
    public static AppAuthFilter getFilter(AuthenticationManager authenticationManager) {
        AppAuthFilter authenFilter = new AppAuthFilter();
        authenFilter.setAuthenticationManager(authenticationManager);
        authenFilter.setAuthenticationSuccessHandler(authenFilter.successHandler);
        authenFilter.setAuthenticationFailureHandler(authenFilter.failureHandler);
        return authenFilter;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            Login user = jsonWrapper.readValue(request.getReader(), Login.class);
            UsernamePasswordAuthenticationToken authenRequest = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

            return getAuthenticationManager().authenticate(authenRequest);
        } catch (IOException e) {
            e.printStackTrace();
            throw new InternalAuthenticationServiceException("Failed to parse authentication request body");
        }
    }

    private AuthenticationSuccessHandler successHandler = (httpServletRequest, httpServletResponse, authentication) ->
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
    private AuthenticationFailureHandler failureHandler = (httpServletRequest, httpServletResponse, authentication) ->
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "username or password is not exist");
}