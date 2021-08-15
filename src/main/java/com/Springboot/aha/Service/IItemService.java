package com.Springboot.aha.Service;

import com.Springboot.aha.DTO.ItemDTO;
import com.Springboot.aha.Entity.ITemEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IItemService {
    ItemDTO save(ItemDTO item);
    ItemDTO update(ItemDTO item);
    int delete(int id);
    List<ItemDTO> findAll();

    ItemDTO findById(int id);
}
