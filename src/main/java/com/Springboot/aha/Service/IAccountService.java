package com.Springboot.aha.Service;


import com.Springboot.aha.DTO.AccountDTO;
import com.Springboot.aha.DTO.ItemDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IAccountService {

   AccountDTO save(AccountDTO item);
    AccountDTO update(AccountDTO item);
    int delete(int id);
    List<AccountDTO> findAll();

    AccountDTO findById(int id);

}
