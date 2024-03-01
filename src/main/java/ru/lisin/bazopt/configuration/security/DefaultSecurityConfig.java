package ru.lisin.bazopt.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.csrf.*;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ru.lisin.bazopt.filter.GetCSRFFilter;

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
                                    "/registration.html",
                                    "/register",
                                    "/csrf"
                            ).permitAll()
                    ).authorizeHttpRequests(
                            authorizationHttpRequest -> authorizationHttpRequest.anyRequest().authenticated()
                    ).formLogin(
                            formLogin -> formLogin.loginPage("/login.html")
                                    .loginProcessingUrl("/login-process")
                                    .usernameParameter("email")
                                    .passwordParameter("password")
                                    .permitAll()
//                        formLogin -> formLogin.permitAll()
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
//                    csrf -> csrf.disable()
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
