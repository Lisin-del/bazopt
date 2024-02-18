package ru.lisin.bazopt.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class DefaultSecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.authorizeHttpRequests(
//                authorizationHttpRequest -> authorizationHttpRequest.requestMatchers("/getUser").permitAll());

        httpSecurity
                .authorizeHttpRequests(
                        authorizationHttpRequest -> authorizationHttpRequest.requestMatchers("/js/**").permitAll()
                ).authorizeHttpRequests(
                        authorizationHttpRequest -> authorizationHttpRequest.requestMatchers("/css/**").permitAll()
                ).authorizeHttpRequests(
                        authorizationHttpRequest -> authorizationHttpRequest.requestMatchers("/registration.html").permitAll()
                );
        httpSecurity.authorizeHttpRequests(authorizationHttpRequest -> authorizationHttpRequest.anyRequest().authenticated())
                .formLogin(
                        formLogin -> formLogin.loginPage("/login.html")
                                .usernameParameter("userEmail")
                                .passwordParameter("userPassword")
                                .permitAll()
                );

        return httpSecurity.build();
    }
}
