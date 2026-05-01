package com.TodoList.demo.security;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.io.IOException;
import java.util.List;


@Configuration
public class authurization {



    //password encoder(just encoding the password)
    @Bean
    public PasswordEncoder passwordEncoder()throws Exception{
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, filtersuser filtersuser) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()  // ✅ FIRST
                        .requestMatchers("/auth/**").permitAll()                 // ✅ SECOND
                        .anyRequest().authenticated()
                )
                .formLogin(AbstractHttpConfigurer::disable)   // ✅ ADD THIS
                .httpBasic(AbstractHttpConfigurer::disable)   // ✅ ADD THIS
                .addFilterBefore(filtersuser, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration Configuration = new CorsConfiguration();
        Configuration.setAllowedOrigins(List.of("http://localhost:63342"));
        Configuration.setAllowedMethods(List.of("GET","POST","PUT","DELETE","OPTIONS"));
        Configuration.setAllowedHeaders(List.of("*"));
        Configuration.setAllowCredentials(true);



        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",Configuration);

        return source;
    }
}
