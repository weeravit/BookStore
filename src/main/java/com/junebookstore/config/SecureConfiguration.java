package com.junebookstore.config;

import com.junebookstore.common.wrapper.PasswordWrapper;
import com.junebookstore.config.security.AppAuthProvider;
import com.junebookstore.config.security.AppAuthFilter;
import com.junebookstore.config.security.UnauthEntryPoint;
import com.junebookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecureConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    UserService userService;
    @Autowired
    PasswordWrapper passwordWrapper;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(
                AppAuthProvider.getProvider(
                        userService,
                        passwordWrapper.getPasswordEncoder()
                )
        );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .httpBasic().disable()
                .formLogin().disable()
                .exceptionHandling()
                .authenticationEntryPoint(new UnauthEntryPoint())
                .and()
                .addFilterBefore(AppAuthFilter.getFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/books").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers(HttpMethod.POST, "/users").permitAll()
                .antMatchers(
                        "/v2/api-docs",
                        "/swagger-resources",
                        "/swagger-resources/**",
                        "/configuration/ui",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/webjars/**"
                ).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .headers().frameOptions().sameOrigin();
    }
}
