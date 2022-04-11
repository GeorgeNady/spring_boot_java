package com.george.demo.springdemo.springdemo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.george.demo.springdemo.springdemo.security.ApplicationUserPermission.*;
import static com.george.demo.springdemo.springdemo.security.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                /**
                 *TODO:
                 * when you use CSRF protection?
                 * * it is recommended to use CSRF protection for any request that could be processed by a browser by normal user
                 * * if you only creating a service that is used by non-browser clint you will likely want to disable CSRF protection
                 */
                .csrf().disable()

                .authorizeRequests()

                // note: when add antMatchers ORDER DOSE MATTERS
                // for this patterns ... we will permit all
                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()

                .antMatchers("/api/**").hasRole(ADMIN.name())

                .antMatchers(HttpMethod.DELETE, "/management/api/**").hasAuthority(COURSE_WRITE.name())
                .antMatchers(HttpMethod.POST, "/management/api/**").hasAuthority(COURSE_WRITE.name())
                .antMatchers(HttpMethod.PUT, "/management/api/**").hasAuthority(COURSE_WRITE.name())

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
                .password(passwordEncoder.encode("1234"))
                // ROLE_USER
                // TODO: how to use it
                .roles(ADMIN.name())
                .build();

        UserDetails josephUserDetails = User.builder()
                .username("joseph")
                .password(passwordEncoder.encode("1234"))
                .roles(STUDENT.name())
                .build();

        UserDetails nadyUserDetails = User.builder()
                .username("nady")
                .password(passwordEncoder.encode("1234"))
                .roles(TEACHER.name())
                .build();

        return new InMemoryUserDetailsManager(georgeUserDetails, josephUserDetails, nadyUserDetails);
    }
}