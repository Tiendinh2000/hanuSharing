package com.Springboot.aha.Repository;

import com.Springboot.aha.Entity.Category;
import com.Springboot.aha.Entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Category,Integer> {
    Category findById(int id);
}
