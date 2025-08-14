package com.example.jobportal.jwt;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class JwtAuthenticationFilter  extends OncePerRequestFilter{

    @Autowired
    private  JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected  boolean shouldNotFilter(HttpServletRequest request){
        String path = request.getServletPath();
        return path.startsWith("api/auth");
    }

    // this function is for filtering jwt token in the request
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
                String authHeader = request.getHeader("Authorization");

                if(authHeader != null && authHeader.startsWith("Bearer ")){
                    String jwt = authHeader.substring(7);

                    if(jwtUtil.validateToken(jwt)){
                        String email = jwtUtil.extractEmail(jwt); 
                        String role = jwtUtil.extractRole(jwt);

                        UserDetails UserDetails = userDetailsService.loadUserByUsername(email);

                        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                                        UserDetails, null, List.of(new SimpleGrantedAuthority("ROLE_"+ role)));

                        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                        SecurityContextHolder.getContext().setAuthentication(authToken);

                    }
                }
                filterChain.doFilter(request, response);
    }

}



    
