package com.Springboot.aha.API;

import com.Springboot.aha.DTO.LoginRequest;
import com.Springboot.aha.DTO.MessageResponse;
import com.Springboot.aha.Entity.User;
import com.Springboot.aha.Entity.UserDetailsImpl;
import com.Springboot.aha.Exception.User.InvalidUsernameOrPasswordException;
import com.Springboot.aha.Security.JwtUtils;
import com.Springboot.aha.Service.impl.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthenticationAPI {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserService accountService;

    @PostMapping("/signin")
    public ResponseEntity<?> SignIn(@Valid @RequestBody User loginRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errors = bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.joining(" ,"));
            throw new InvalidUsernameOrPasswordException(errors);
        }

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

            String jwt = jwtUtils.generateToken(userDetails);

            Map<String, String> response = new HashMap<>();
            response.put("jwt", jwt);
            return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<?> insert(@Valid @RequestBody User model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errors = bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.joining(" ,"));
            throw new InvalidUsernameOrPasswordException(errors);
        } else {
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/auth/signup").toUriString());
            return ResponseEntity.created(uri).body(accountService.save(model));
        }
    }
}
