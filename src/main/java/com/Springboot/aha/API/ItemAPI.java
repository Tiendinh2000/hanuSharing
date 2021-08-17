package com.Springboot.aha.API;

import com.Springboot.aha.Entity.ITemEntity;
import com.Springboot.aha.Service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/items")
public class ItemAPI {

    @Autowired
    private IItemService itemService;


    @PostMapping(value = "/add")
    public ITemEntity createItem(@RequestBody ITemEntity model) {
        return itemService.save(model);
    }

    @GetMapping( "/get")
    public List<ITemEntity> getItems() {
        return  itemService.findAll();
    }

    @PutMapping(value = "/update/{id}")
    public ITemEntity editITem(@RequestBody ITemEntity model, @PathVariable("id") int id) {
        return itemService.update(model);
    }

    @DeleteMapping(value = "/delete/{id}")
    public int deleteItem(@PathVariable("id") int id) {
        return itemService.delete(id);
    }

    @GetMapping("/getById")
    public List<ITemEntity> getItemByAccountId(@RequestParam("id") int id){
        return itemService.findByAccount(id);
    }

}

