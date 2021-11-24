package com.Springboot.aha.API.Admin;

import com.Springboot.aha.DTO.MessageResponse;
import com.Springboot.aha.Entity.User;
import com.Springboot.aha.Service.IItemService;
import com.Springboot.aha.Service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/admin/account-controller")
public class AccountControllerAPI {

    @Autowired
    private IItemService itemService;

    @Autowired
    private UserService accountService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping(value = "/get")
    public ResponseEntity<List<User>> getAccount() {
        List<User> accountList = accountService.findAll();
        return ResponseEntity.ok(accountList);
    }


    @GetMapping(value = "/find-by-name")
    public ResponseEntity<User> findUsersByName() {
        User accountList = accountService.getUserByUsername("user");
        return ResponseEntity.ok(accountList);
    }

//    @GetMapping(value = "/find-by-role")
//    public ResponseEntity<List<User>> findUsersByRole() {
//        List<User> accountList = accountService.getUserByRole("ROLE_USER");
//        return ResponseEntity.ok(accountList);
//    }

    @PostMapping(value = "/add")
    public ResponseEntity<?> insert(@Valid @RequestBody User model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errors = bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.joining(" ,"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse(errors.toString()));
        } else {
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/add").toUriString());
            return ResponseEntity.created(uri).body(accountService.save(model));
        }
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<User> update(@RequestBody User model, @PathVariable("id") int id) {
        try {
            model.setUser_id(id);
            User updated = accountService.update(model);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<Integer> delete(@RequestParam("id") int ids) {

        int id = accountService.delete(ids);
        if (id > 0)
            return ResponseEntity.ok(id);
        else
            return new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);

    }


}
