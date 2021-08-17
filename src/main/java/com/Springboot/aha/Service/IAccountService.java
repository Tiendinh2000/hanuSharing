package com.Springboot.aha.Service;


import com.Springboot.aha.Entity.AccountEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IAccountService {

   AccountEntity save(AccountEntity item);
    AccountEntity update(AccountEntity item);
    int delete(int id);
    List<AccountEntity> findAll();

}
