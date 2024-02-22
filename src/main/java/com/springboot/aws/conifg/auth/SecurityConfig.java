package com.springboot.aws.conifg.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.springboot.aws.domain.user.Role;

import static org.springframework.security.config.Customizer.withDefaults;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig{
    
    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests()
            .requestMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
            .requestMatchers("/api/v1/**").hasRole(Role.USER.name())
            .anyRequest().authenticated();
        
        http.logout().logoutSuccessUrl("/");
        http.oauth2Login().userInfoEndpoint().userService(customOAuth2UserService);
            
        return http.build();
    }
}
