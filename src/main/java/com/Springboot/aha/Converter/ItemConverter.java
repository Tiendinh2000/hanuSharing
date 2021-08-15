package com.Springboot.aha.Converter;


import com.Springboot.aha.DTO.ItemDTO;
import com.Springboot.aha.Entity.AccountEntity;
import com.Springboot.aha.Entity.ITemEntity;
import com.Springboot.aha.Repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemConverter {


    @Autowired
    private IAccountRepository accountRepository;

    public ITemEntity toItemEntity(ItemDTO dto){
        ITemEntity entity = new ITemEntity();
       AccountEntity accountEntity = accountRepository.findById(dto.getAccountId()).get();
       entity.setAccount(accountEntity);
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        return entity;
    }

    public  ItemDTO toItemDTO(ITemEntity entity){
        ItemDTO dto = new ItemDTO();
        dto.setId(entity.getItem_id());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        dto.setAccountId(entity.getAccount().getAccount_id());
        return  dto;
    }

    public ITemEntity toItemEntity(ItemDTO dto, ITemEntity entity){
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        return  entity;
    }

}
