package com.Springboot.aha.API;

import com.Springboot.aha.DTO.AccountDTO;
import com.Springboot.aha.DTO.ItemDTO;
import com.Springboot.aha.Service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class ItemAPI {

    @Autowired
    private IItemService itemService;


    @PostMapping(value = "/api/items/insert")
    public ItemDTO createItem(@RequestBody ItemDTO model) {
        return itemService.save(model);
    }

    @GetMapping( "/api/items")
    public List<ItemDTO> getItems() {
        return  itemService.findAll();
    }

    @PutMapping(value = "/api/items/{id}")
    public ItemDTO editITem(@RequestBody ItemDTO model, @PathVariable("id") int id) {
        model.setId(id);
        return itemService.update(model);
    }

    @DeleteMapping(value = "/api/items/{id}")
    public int deleteItem(@PathVariable("id") int id) {
        return itemService.delete(id);
    }

    @GetMapping("/api/items/{id}")
    public ItemDTO getItemById(@PathVariable("id") int id){
        return itemService.findById(id);
    }

}

