package com.Springboot.aha.Service;

import com.Springboot.aha.Entity.Category;
import com.Springboot.aha.Entity.Item;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IItemService {
    Item save(Item item);

    Item update(Item item);

    Item remove(Item item);

    List<Item> findAll();

    Item findById(int id);

    List<Item> findByAccountID(int id);

    List<Item> findItemByCategory(int category);

    List<Item> findByPrice(int price);
}
