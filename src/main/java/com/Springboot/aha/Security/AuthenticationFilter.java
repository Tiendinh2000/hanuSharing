package com.Springboot.aha.Security;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
public class AuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtils jwtUtils;


    private final String Bearer = "Bearer ";


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader(AUTHORIZATION);

        if (request.getServletPath().equals("/auth/signin") || request.getServletPath().equals("/auth/signup") || isSwaggerResource(request.getServletPath())) {
            filterChain.doFilter(request, response);
            return;
        }

        if (authorizationHeader == null || !authorizationHeader.startsWith(Bearer)) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authorizationHeader.substring(Bearer.length());

        try {
            if (!jwtUtils.validToken(token)) {
                throw new RuntimeException("Invalid token");
            }
            String username = jwtUtils.getUserName(token);
            String[] roles = jwtUtils.getRoles(token);
            UsernamePasswordAuthenticationToken authenticationToken = getUsernamePasswordAuthenticationToken(username, roles);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            filterChain.doFilter(request, response);

        } catch (Exception e) {
            log.error("Error login {}", e.getMessage());
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(response.getOutputStream(), errorResponse);
        }
    }


    private boolean isSwaggerResource(String URL) {
        String[] patterns = {"/v2/api-docs", "/configuration/ui", "/swagger-resources","/swagger-resources/.*", "/configuration/security", "/swagger-ui.html", "/swagger-ui.*", "/webjars/.*", "/swagger/.*","/null/swagger-resources/.*", "/csrf"};

        for (String pattern : patterns) {
            if (URL.matches(pattern)) {
                return true;
            }
        }
        return false;
    }

    private UsernamePasswordAuthenticationToken getUsernamePasswordAuthenticationToken(String username, String[] roles) {

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        stream(roles).forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role));
        });
        return new UsernamePasswordAuthenticationToken(username, null, authorities);
    }

}
