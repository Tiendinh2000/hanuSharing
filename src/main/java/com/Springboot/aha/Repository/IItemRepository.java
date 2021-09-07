package com.Springboot.aha.Repository;

import com.Springboot.aha.Entity.Category;
import com.Springboot.aha.Entity.User;
import com.Springboot.aha.Entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IItemRepository extends JpaRepository<Item,Integer> {
     List<Item> findByUser(User accountDTO);
     List<Item> findAllByCategory(Category category);
}
