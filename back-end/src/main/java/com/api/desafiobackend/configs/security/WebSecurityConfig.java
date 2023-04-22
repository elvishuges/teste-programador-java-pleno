package com.api.desafiobackend.configs.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        System.out.println(String.format("Hellodd, %s!", http));
        // http
        // .httpBasic()
        // .and()
        // .authorizeHttpRequests()
        // // .antMatchers(HttpMethod.GET, "/parking-spot/**").permitAll()
        // // .antMatchers(HttpMethod.POST, "/parking-spot").hasRole("USER")
        // // .antMatchers(HttpMethod.DELETE, "/parking-spot/**").hasRole("ADMIN")
        // .anyRequest().permitAll()
        // .and()
        // .csrf().disable();

        // http
        // .authorizeHttpRequests((requests) -> requests
        // .requestMatchers(HttpMethod.POST, "/api/auth").permitAll()
        // .anyRequest().authenticated());

        http.httpBasic().and().csrf().disable()
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(HttpMethod.POST, "/api/auth").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/signup").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/products").permitAll().anyRequest().authenticated());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        System.out.println(String.format("Hellodd, %s!", config));
        return config.getAuthenticationManager();
    }

}