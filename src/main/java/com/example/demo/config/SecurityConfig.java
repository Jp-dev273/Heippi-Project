package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .formLogin(Customizer.withDefaults())
                .authorizeHttpRequests((request) ->
                        request
                                .requestMatchers("/login").permitAll()
                                .requestMatchers("/h2-console/**").permitAll()
                                .requestMatchers("/users/password").permitAll()
                                .requestMatchers("/users/activation").permitAll()
                                .requestMatchers("/hospital").hasAuthority(String.valueOf(Authorities.HOSPITAL))
                                .requestMatchers("/physicians").hasAuthority(String.valueOf(Authorities.PHYSICIAN))
                                .requestMatchers("/patients").hasAuthority(String.valueOf(Authorities.PATIENT))
                                .anyRequest().authenticated()
        )
                .csrf(AbstractHttpConfigurer::disable)
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
