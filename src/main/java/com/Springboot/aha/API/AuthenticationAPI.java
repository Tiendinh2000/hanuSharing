package com.Springboot.aha.API;

import com.Springboot.aha.DTO.LoginRequest;
import com.Springboot.aha.Entity.User;
import com.Springboot.aha.Entity.UserDetailsImpl;
import com.Springboot.aha.Exception.BindingResultException.BindingResultException;
import com.Springboot.aha.Exception.User.UsernameOrPasswordIsInvalidException;
import com.Springboot.aha.Security.JwtUtils;
import com.Springboot.aha.Service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthenticationAPI {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserService accountService;

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

            Map<String, Object> jwt = jwtUtils.generateToken(userDetails);
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());
            return ResponseEntity.ok(jwt);
        } catch (Exception e) {
            throw new UsernameOrPasswordIsInvalidException("invalid username or password!");
        }
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<?> signUp(@Valid @RequestBody User model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errors = BindingResultException.getErrorMessage(bindingResult);
            throw new UsernameOrPasswordIsInvalidException(errors);
        } else {
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/auth/signup").toUriString());
            return ResponseEntity.created(uri).body(accountService.save(model));
        }
    }
}
