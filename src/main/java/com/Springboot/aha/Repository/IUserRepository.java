package com.Springboot.aha.Repository;

import com.Springboot.aha.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User,Integer> {
    User findByUserName(String username);
}
