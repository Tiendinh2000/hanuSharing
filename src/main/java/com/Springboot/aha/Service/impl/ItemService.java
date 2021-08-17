package com.Springboot.aha.Service.impl;

import com.Springboot.aha.Entity.AccountEntity;
import com.Springboot.aha.Entity.ITemEntity;
import com.Springboot.aha.Repository.IAccountRepository;
import com.Springboot.aha.Repository.IItemRepository;
import com.Springboot.aha.Service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService implements IItemService {

    @Autowired
    private IItemRepository itemRepository;

    @Autowired
    private IAccountRepository accountRepository;


    @Override
    public ITemEntity save(ITemEntity item) {
        return itemRepository.save(item);
    }

    @Override
    public ITemEntity update(ITemEntity newItem) {
        if (itemRepository.existsById(newItem.getItem_id())) {
            ITemEntity old = itemRepository.findById(newItem.getItem_id()).get();
            if (newItem.getName() != null)
                old.setName(newItem.getName());
            if (newItem.getPrice() != old.getPrice())
                old.setPrice(newItem.getPrice());
            if (newItem.getAccount() != null)
                old.setAccount(newItem.getAccount());
        }
        return null;
    }

    @Override
    public int delete(int id) {
        try {
            ITemEntity iTemEntity = itemRepository.getOne(id);
            itemRepository.delete(iTemEntity);
            return id;
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public List<ITemEntity> findAll() {
        return itemRepository.findAll();
    }

    @Override
    public List<ITemEntity> findByAccount(int id) {
        if (accountRepository.existsById(id)) {
            AccountEntity account = accountRepository.findById(id).get();
            return itemRepository.findByAccount(account);
        } else
            return null;
    }
}