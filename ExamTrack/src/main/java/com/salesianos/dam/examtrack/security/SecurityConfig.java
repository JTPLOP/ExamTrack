package com.salesianos.dam.examtrack.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
	private final PasswordEncoder passwordEncoder;
    

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                        (authz) -> authz.requestMatchers("/login","/css/**", "/js/**", "/h2-console/**","/image/**").permitAll()
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                .anyRequest().authenticated())
                .formLogin((loginz) -> loginz
                        .loginPage("/login").permitAll()
                        .defaultSuccessUrl("/inicio", true)
                        .permitAll())

                .logout((logoutz) -> logoutz
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .permitAll());

        
        /*Este fragmento nos permitira acceder a la base de datos sin problemas mientras incorporamos la seguridad.*/
        http.csrf(csrfz -> csrfz.disable());
        http.headers(headersz -> headersz
                .frameOptions(frameOptionsz -> frameOptionsz.disable()));

        return http.build();
    }

}