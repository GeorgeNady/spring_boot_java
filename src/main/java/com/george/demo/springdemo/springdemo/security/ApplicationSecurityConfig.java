package com.george.demo.springdemo.springdemo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.george.demo.springdemo.springdemo.security.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()

                // for this patterns ... we will permit all
                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()

                .antMatchers("api/**").hasRole(ADMIN.name())

                .anyRequest()
                .authenticated()
                .and()

                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        // is how you retrieve your users from database
        UserDetails georgeUserDetails = User.builder()
                // USER and PASS
                .username("george")
                .password(passwordEncoder.encode("01211422185"))
                // ROLE_USER
                // TODO: how to use it
                .roles(ADMIN.name())
                .build();

        UserDetails josephUserDetails = User.builder()
                .username("joseph")
                .password(passwordEncoder.encode("01270680830"))
                .roles(STUDENT.name())
                .build();

        return new InMemoryUserDetailsManager(georgeUserDetails, josephUserDetails);
    }
}