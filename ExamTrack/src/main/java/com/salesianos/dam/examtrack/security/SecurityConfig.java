package com.salesianos.dam.examtrack.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    



    



    /* MEDIANTE CACHE ORDENADOR (NO IDENTIDADES)

    @Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) {

		http.authorizeHttpRequests(
				(authz) -> authz
					.requestMatchers("/producto/**").authenticated()
					.requestMatchers("/login", "/css/**", "/js/**", "/image/**").permitAll()
					.anyRequest()
					.authenticated())
				.formLogin(form -> form
						.loginPage("/login")
						.permitAll()
				);

		
		http.csrf((csrf) -> {
			csrf.ignoringRequestMatchers("/h2/**");
		});
		http.headers((headers) -> headers.frameOptions((opts) -> opts.disable()));

		return http.build();
	}
    

    
    @Bean
    UserDetailsService UserDetailsService () {
        InMemoryUserDetailsManager manager =
            new InMemoryUserDetailsManager();

            UserDetails user = User.builder() 
                .username("user")
                .password("{bcrypt}user")
                .roles("USER")
                .build();

            UserDetails admin = User.builder() 
                .username("admin")
                .password("{bcrypt}admin")
                .roles("ADMIN")
                .build();
            

                manager.createUser(user);
                manager.createUser(admin);

            return manager;
    }

    */









}
