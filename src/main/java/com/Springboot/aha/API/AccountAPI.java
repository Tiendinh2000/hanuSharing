package com.Springboot.aha.API;

import com.Springboot.aha.DTO.AccountDTO;
import com.Springboot.aha.Service.IItemService;
import com.Springboot.aha.Service.impl.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class AccountAPI {

    @Autowired
    private IItemService itemService;


    @PostMapping(value = "/new")
    public AccountDTO insert(@RequestBody AccountDTO model) {
        return model;
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
    public String getAccount(){
        return "https://wallpapercave.com/wp/wp4972362.jpg";
    }
}
