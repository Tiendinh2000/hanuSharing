package com.Springboot.aha.Service.impl;

import com.Springboot.aha.Entity.AccountEntity;
import com.Springboot.aha.Repository.IAccountRepository;
import com.Springboot.aha.Service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService implements IAccountService {

    @Autowired
    private IAccountRepository accountRepository;


    @Override
    public AccountEntity save(AccountEntity item) {
        return accountRepository.save(item);
    }

    @Override
    public AccountEntity update(AccountEntity newAccount) {

        if(accountRepository.existsById(newAccount.getAccount_id())){
            AccountEntity old = accountRepository.findById(newAccount.getAccount_id()).get();
            if(newAccount.getUserName()!=null)
                old.setUserName(newAccount.getUserName());
            if(newAccount.getPassword()!=null)
                old.setPassword(newAccount.getPassword());
            if(newAccount.getItemList()!=null)
                old.setItemList(newAccount.getItemList());
          return  accountRepository.save(old);
        }
        else
           return null;
    }

    @Override
    public int delete(int id) {
       try {
           AccountEntity entity = accountRepository.findById(id).get();
           accountRepository.delete(entity);
           return id;
       }catch (Exception e){
       return -1;}
    }

    @Override
    public List<AccountEntity> findAll() {
        return accountRepository.findAll();
    }


}
