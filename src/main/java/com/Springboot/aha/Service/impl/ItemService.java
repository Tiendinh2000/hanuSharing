package com.Springboot.aha.Service.impl;

import com.Springboot.aha.Converter.ItemConverter;
import com.Springboot.aha.DTO.ItemDTO;
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

    @Autowired
    private ItemConverter itemConverter;

    @Override
    public ItemDTO save(ItemDTO item) {
        ITemEntity entity = itemConverter.toItemEntity(item);
        itemRepository.save(entity);
        return itemConverter.toItemDTO(entity);
    }

    @Override
    public ItemDTO update(ItemDTO item) {
        ITemEntity oldEntity = itemRepository.getOne(item.getId());
        ITemEntity newEntity = itemConverter.toItemEntity(item, oldEntity);
        itemRepository.save(newEntity);
        return itemConverter.toItemDTO(newEntity);
    }

    @Override
    public int delete(int id) {
        try{
           ITemEntity entity = itemRepository.findById(id).get();
            itemRepository.delete(entity);
            return id;
        }catch (Exception e) {
            return -1;
        }
    }

    @Override
    public List<ItemDTO> findAll() {
        List<ITemEntity> l = itemRepository.findAll();
        List result = new ArrayList();
        for (ITemEntity entity : l) {
            result.add(itemConverter.toItemDTO(entity));
        }
        return result;
    }

    @Override
    public ItemDTO findById(int id) {
        ITemEntity entity = itemRepository.findById(id).get();
        return itemConverter.toItemDTO(entity);
    }


}
