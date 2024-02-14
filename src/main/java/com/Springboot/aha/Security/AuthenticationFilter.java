package com.Springboot.aha.Security;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

      if (request.getServletPath().equals("/auth/signin") || request.getServletPath().equals("/auth/signup") || isSwaggerResource(request.getServletPath())) {
            filterChain.doFilter(request, response);
        } else {
            String authorizationHeader = request.getHeader(AUTHORIZATION);
            String token = authorizationHeader.substring(Bearer.length());
            if (authorizationHeader != null && authorizationHeader.startsWith(Bearer) && !jwtUtils.validToken(token)) {
                try {
                    String username = jwtUtils.getUserName(token);
                    String[] roles = jwtUtils.getRoles(token);
                   // int id = jwtUtils.getId(token);

                    UsernamePasswordAuthenticationToken authenticationToken = getUsernamePasswordAuthenticationToken(username, roles);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    filterChain.doFilter(request, response);
                } catch (Exception e) {
                    log.error("Error login {}", e.getMessage());
                    e.printStackTrace();
                    response.setStatus(FORBIDDEN.value());
                    response.setHeader("error", e.getMessage());
                    Map<String, String> errorToken = new HashMap<>();
                    errorToken.put("error_token", e.getMessage());
                    response.setContentType(APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(), errorToken);
                }

            } else {
                filterChain.doFilter(request, response);
            }
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
