package com.Springboot.aha.Service.impl;

import com.Springboot.aha.Entity.Category;
import com.Springboot.aha.Entity.Item;
import com.Springboot.aha.Entity.User;
import com.Springboot.aha.Exception.User.InternalException;
import com.Springboot.aha.Exception.User.PermissionDeniedException;
import com.Springboot.aha.Repository.ICategoryRepository;
import com.Springboot.aha.Repository.IItemRepository;
import com.Springboot.aha.Repository.IUserRepository;
import com.Springboot.aha.Service.IItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ItemService implements IItemService {

    @Autowired
    private IItemRepository itemRepository;

    @Autowired
    private IUserRepository accountRepository;

    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public Item save(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public Item update(Item newItem) {
        if (itemRepository.existsById(newItem.getItem_id())) {
            Item old = itemRepository.findById(newItem.getItem_id()).get();
            if (newItem.getName() != null)
                old.setName(newItem.getName());
            if (newItem.getPrice() != old.getPrice())
                old.setPrice(newItem.getPrice());
            return itemRepository.save(old);
        }
        else throw new RuntimeException("Item not found!");
    }

    @Override
    public Item remove(Item item) {

        try {
            itemRepository.delete(item);
            return item;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new InternalException("Can not delete item!");
        }
    }


    @Override
    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    @Override
    public Item findById(int id) {
        try {
            return itemRepository.findById(id).get();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("Item not found!");
        }
    }

    @Override
    public List<Item> findByAccountID(int id) {
        if (accountRepository.existsById(id)) {
            User account = accountRepository.findById(id).get();
            return itemRepository.findByUser(account);
        } else
              throw new RuntimeException("Item not found!");
    }

    @Override
    public List<Item> findItemByCategory(int categoryID) {
        try {
            Category category = categoryRepository.findById(categoryID);
            return itemRepository.findAllByCategory(category);
        }catch (Exception e) {
            throw new InternalException("Can not find Item!");
        }
    }

    @Override
    public List<Item> findByPrice(int price) {
        return itemRepository.findByPrice(price);
    }


}