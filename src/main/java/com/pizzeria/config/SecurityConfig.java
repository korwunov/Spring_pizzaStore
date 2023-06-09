package com.pizzeria.config;

import com.pizzeria.services.CustomUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public static BCryptPasswordEncoder passEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests(requests ->
//                requests.requestMatchers("/", "/user/registration", "/images/**", "/styles/**").permitAll()
//                        .requestMatchers("/pizza/action").hasAnyAuthority("ADMIN", "EMPLOYEE")
//                        .anyRequest())
//                .formLogin((form) -> form
//                        .loginPage("/login")
//                        .defaultSuccessUrl("/")
//                        .permitAll()
//                )
//                .logout()
//                .logoutSuccessUrl("/");
        http.authorizeHttpRequests((auth) -> {
            try {
                auth.requestMatchers("/", "/user/registration", "/images/**", "/styles/**").permitAll()
                        .requestMatchers("/user/delete{ID}", "/user", "/get{ID}", "/pizza/action", "/changeOrderStatus/{status}/{orderID}/").hasAnyAuthority("ROLE_ADMIN", "ROLE_EMPLOYEE")
                        .requestMatchers("/user/setAdminRole/{userID}", "/user/setEmployeeRole/{userID}").hasAnyAuthority("ROLE_ADMIN")
                        .anyRequest().authenticated().and().formLogin();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).csrf().disable();

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailService();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
        dao.setUserDetailsService(userDetailsService());
        dao.setPasswordEncoder(passEncoder());
        return dao;
    }
}
