package com.TodoList.demo.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class filtersuser extends OncePerRequestFilter {
    @Autowired
    private Jwttoken jwtutil;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authheader = request.getHeader("Authorization");


        String path = request.getServletPath();

        // ✅ Allow preflight requests
        if (request.getMethod().equalsIgnoreCase("OPTIONS")) {
            filterChain.doFilter(request, response);
            return;
        }
        // ✅ Allow auth endpoints without token
        if (path.startsWith("/auth")) {
            filterChain.doFilter(request, response);
            return;
        }


        if(authheader != null && authheader.startsWith("Bearer ")){
            String token = authheader.substring(7);
            if (jwtutil.validatetoken(token)){
                String email = jwtutil.extractemail(token);
                var auth = new UsernamePasswordAuthenticationToken(email,null, List.of());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        filterChain.doFilter(request,response);
    }
}
