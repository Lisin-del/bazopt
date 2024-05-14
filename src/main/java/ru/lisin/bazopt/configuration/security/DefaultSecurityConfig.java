package ru.lisin.bazopt.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.csrf.*;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ru.lisin.bazopt.httpfilter.GetCSRFFilter;

@EnableWebSecurity
//@EnableMethodSecurity
@Configuration
public class DefaultSecurityConfig {
    private final UserDetailsService userDetailsService;

    public DefaultSecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {
        try {
            httpSecurity
                    .authorizeHttpRequests(
                            authorizationHttpRequest -> authorizationHttpRequest.requestMatchers(
                                    "/js/**",
                                    "/css/**",
                                    "/registration",
                                    "/register",
                                    "/login",
                                    "/csrf",
                                    "/login/process"
                            ).permitAll()
                    ).authorizeHttpRequests(
                            authorizationHttpRequest -> authorizationHttpRequest.anyRequest().authenticated()
                    ).formLogin(
                            formLogin -> formLogin.loginPage("/login")
                                    .loginProcessingUrl("/login/process")
                                    .usernameParameter("email")
                                    .passwordParameter("password")
                                    .permitAll()
                    ).logout(
                            logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                    .clearAuthentication(true)
                                    .invalidateHttpSession(true)
                                    .permitAll()
                    );
            HttpSessionCsrfTokenRepository httpSessionCsrfTokenRepository = new HttpSessionCsrfTokenRepository();
            httpSecurity.addFilterAfter(new GetCSRFFilter(), ExceptionTranslationFilter.class);
            httpSecurity.csrf(
                    csrf -> csrf.csrfTokenRequestHandler(new CSRFTokenRequestHandler())
                            .csrfTokenRepository(httpSessionCsrfTokenRepository)
                            .sessionAuthenticationStrategy(new CsrfAuthenticationStrategy(httpSessionCsrfTokenRepository))
                            .ignoringRequestMatchers(new AntPathRequestMatcher("/register", HttpMethod.POST.name()))
            );
            return httpSecurity.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Bean
    @Autowired
    public DaoAuthenticationProvider daoAuthenticationProvider(PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        return daoAuthenticationProvider;
    }
}
