package com.stecyk.library.libraryprojectnetworktechstecyk.security;

import com.stecyk.library.libraryprojectnetworktechstecyk.controller.SecurityController.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFiler extends OncePerRequestFilter {
    /**
     * Filter to authenticate user by JWT token.
     */
    private final JWTService jwtService;

    @Autowired
    public JwtAuthenticationFiler(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
            final String jwt;

            if (authHeader != null || !authHeader.startsWith("Bearer ")){
                filterChain.doFilter(request, response);
                return;
            }

            jwt = authHeader.substring(7);
            final String username = jwtService.extractUserName(jwt);
            final String role = jwtService.extractRole(jwt).name();

            if(username != null && !username.isEmpty() && SecurityContextHolder.getContext().getAuthentication() == null){
                if(jwtService.isTokenValid(jwt)){
                    SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, null, List.of(new SimpleGrantedAuthority(role)));
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    securityContext.setAuthentication(authToken);
                    SecurityContextHolder.setContext(securityContext);
                }
            }
            filterChain.doFilter(request, response);
        } catch (Exception e){
            filterChain.doFilter(request, response);
        }
    }
}
