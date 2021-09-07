package com.Springboot.aha.Repository;

import com.Springboot.aha.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Role,Integer> {
    Role findByName(String userName);
}
