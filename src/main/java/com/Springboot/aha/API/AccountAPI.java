package com.Springboot.aha.API;

import com.Springboot.aha.DTO.AccountDTO;
import com.Springboot.aha.Service.IItemService;
import com.Springboot.aha.Service.impl.AccountService;
import com.Springboot.aha.Service.impl.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class AccountAPI {

    @Autowired
    private IItemService itemService;

    @Autowired
    private AccountService accountService;

    @PostMapping(value = "/new")
    public AccountDTO insert(@RequestBody AccountDTO model) {
      return  accountService.save(model);
    }

    @PutMapping(value = "/new/{id}")
    public AccountDTO update(@RequestBody AccountDTO model, @PathVariable("id") int id) {
        System.out.println(id);
        return model;
    }

    @DeleteMapping(value = "/new")
    public void delete (@RequestBody int ids) {
        System.out.println("dêle"+ids);
    }

    @GetMapping(value = "/new")
    public ResponseEntity<List<AccountDTO>> getAccount(){
        List accountList = accountService.findAll();
        return ResponseEntity.ok(accountList);
    }
}
