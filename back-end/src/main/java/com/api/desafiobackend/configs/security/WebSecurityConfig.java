package com.api.desafiobackend.configs.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain config(HttpSecurity http) throws Exception {

        http
                .httpBasic()
                .and()
                .authorizeHttpRequests()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();

        return http.build();

    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.build();
    }

    /**
     * o bean significa que, quando o spring boot ler essa anotação, ele vai
     * verificar esse metodo
     * e vai executar e deixar disponivel. vamo utilizar também quando cadastra um
     * usuário an aplicação
     *
     * @return
     */

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}