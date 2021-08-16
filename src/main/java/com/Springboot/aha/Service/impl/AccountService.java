package com.Springboot.aha.Service.impl;

import com.Springboot.aha.Converter.AccountConverter;
import com.Springboot.aha.DTO.AccountDTO;
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

    @Autowired
    private AccountConverter accountConverter;

    @Override
    public AccountDTO save(AccountDTO accountDTO) {
        AccountEntity entity = accountConverter.toEntity(accountDTO);
        accountRepository.save(entity);
        return accountConverter.toDTO(entity);
    }

    @Override
    public AccountDTO update(AccountDTO item) {
        return null;
    }

    @Override
    public int delete(int id) {
        return 0;
    }

    @Override
    public List<AccountDTO> findAll() {
        List<AccountEntity> list = accountRepository.findAll();
        List<AccountDTO> result = new ArrayList<>();
        for (AccountEntity entity : list) {
           result.add(accountConverter.toDTO(entity));
        }
        return result;
    }

    @Override
    public AccountDTO findById(int id) {
        return null;
    }


}
