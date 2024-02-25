package com.Springboot.aha.API.User;

import com.Springboot.aha.DTO.MessageResponse;
import com.Springboot.aha.Entity.User;
import com.Springboot.aha.Exception.BindingResultException.BindingResultException;
import com.Springboot.aha.Security.JwtUtils;
import com.Springboot.aha.Service.IUserService;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;


@CrossOrigin
@RestController
@RequestMapping("/api/user/account")
public class AccountAPI {
    @Autowired
    private IUserService userService;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    /* Get token from resquest for authentication */
    private String getAuthToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        return authorizationHeader.substring(JwtUtils.Bearer.length());
    }

    @PutMapping(value = "/change-password")
    public ResponseEntity<?> changePassword(@Valid @RequestBody User model, BindingResult bindingResult, HttpServletRequest request) {

        String token;
        if ((token = getAuthToken(request)) == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("You are not signed in!"));
        }
        if (bindingResult.hasErrors()) {
            String errors = BindingResultException.getErrorMessage(bindingResult);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse(errors));
        } else {
            try {
                int userId = jwtUtils.getId(token);
                userService.changePassword(userId, model.getPassword());
                return ResponseEntity.ok(new MessageResponse("change password successfully!"));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse(e.getMessage()));
            }
        }

    }
    
    @PutMapping(value = "/change-username")
    public ResponseEntity<?> changeUserName(@Valid @RequestBody User model, HttpServletRequest request) {

        String token;
        if ((token = getAuthToken(request)) == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("You are not signed in!"));
        } else {
            try {
                int userId = jwtUtils.getId(token);
                model.setUser_id(userId);
                userService.update(model);
                return ResponseEntity.ok(new MessageResponse("change username successfully!"));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse(" bad request"));
            }
        }

    }
//    @PutMapping(value = "/change-phone-number")
//    public ResponseEntity<?> changePhoneNumber(@Valid @RequestBody User model, HttpServletRequest request) {
//
//        String token;
//        if ((token = getAuthToken(request)) == null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("You are not signed in!"));
//        } else {
//            try {
//                int userId = jwtUtils.getId(token);
//                model.setUser_id(userId);
//                userService.update(model);
//                return ResponseEntity.ok(new MessageResponse("change successfully!"));
//            } catch (Exception e) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse(" bad request"));
//            }
//        }
//
//    }
}
