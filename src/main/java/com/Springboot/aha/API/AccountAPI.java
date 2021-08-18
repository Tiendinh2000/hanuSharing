package com.Springboot.aha.API;

import com.Springboot.aha.Entity.AccountEntity;
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
@RequestMapping("/api/account/")
public class AccountAPI {

    @Autowired
    private IItemService itemService;

    @Autowired
    private AccountService accountService;

    @GetMapping(value = "/get")
    public ResponseEntity<List<AccountEntity>> getAccount() {
        List accountList = accountService.findAll();
        return ResponseEntity.ok(accountList);
    }

    @PostMapping(value = "/add")
    public AccountEntity insert(@RequestBody AccountEntity model) {
        return accountService.save(model);
    }

    @PutMapping(value = "/edit/{id}")
    public ResponseEntity<AccountEntity> update(@RequestBody AccountEntity model, @PathVariable("id") int id) {
        try {
            model.setAccount_id(id);
            AccountEntity updated = accountService.update(model);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            e.printStackTrace();
            return (ResponseEntity<AccountEntity>) ResponseEntity.badRequest();
        }

    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<Integer> delete(@RequestParam("id") int ids) {

        int id = accountService.delete(ids);
        if (id >0)
            return ResponseEntity.ok(id);
        else
            return  new ResponseEntity<Integer>(HttpStatus.FORBIDDEN);

    }


}
