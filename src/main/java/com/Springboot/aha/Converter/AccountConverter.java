package com.Springboot.aha.Converter;

import com.Springboot.aha.DTO.AccountDTO;
import com.Springboot.aha.Entity.AccountEntity;
import com.Springboot.aha.Repository.IAccountRepository;
import com.Springboot.aha.Repository.IItemRepository;
import com.Springboot.aha.Service.IItemService;
import com.Springboot.aha.Service.impl.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.text.html.parser.Entity;

@Component
public class AccountConverter {

    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private IItemRepository itemRepository;

    @Autowired
    private IItemService itemService;


    public AccountEntity toEntity(AccountDTO dto) {
        AccountEntity result = new AccountEntity();
        result.setUserName(dto.getUserName());
        result.setPassword(dto.getPassword());
   //     result.setItemList(itemRepository.findByAccount(dto.getId()));
        return  result;
    }

public  AccountDTO toDTO(AccountEntity entity){
        AccountDTO result = new AccountDTO();
        result.setId(entity.getAccount_id());
        result.setUserName(entity.getUserName());
        result.setPassword(entity.getPassword());
        return result;
}

}
