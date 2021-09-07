package com.Springboot.aha.Service.impl;

import com.Springboot.aha.Entity.Category;
import com.Springboot.aha.Entity.User;
import com.Springboot.aha.Entity.Item;
import com.Springboot.aha.Repository.IUserRepository;
import com.Springboot.aha.Repository.IItemRepository;
import com.Springboot.aha.Service.IItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService implements IItemService {

    @Autowired
    private IItemRepository itemRepository;

    @Autowired
    private IUserRepository accountRepository;


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
        return null;
    }

    @Override
    public Item remove(Item iTem) {
        try {
            itemRepository.delete(iTem);
            return iTem;
        }catch (Exception e){
            return  null;
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
       }catch (Exception e){

           return null;
       }
    }

    @Override
    public List<Item> findByAccount(int id) {
        if (accountRepository.existsById(id)) {
            User account = accountRepository.findById(id).get();
            return itemRepository.findByUser(account);
        } else
            return null;
    }

    @Override
    public List<Item> findItemByCategory(Category category) {
        return itemRepository.findAllByCategory(category);
    }


}