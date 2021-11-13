package com.Springboot.aha.Service;


import com.Springboot.aha.Entity.Role;
import com.Springboot.aha.Entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUserService {

    User save(User item);

    User update(User item);

    int delete(int id);

    List<User> findAll();

    Role saveRole(Role role);

    void addRoletoUser(String username, String rolename);

    User getUserByUsername(String username);

    boolean usernameIsExisted(String username);

    User getUserById(int id);

}
