package com.Springboot.aha.API;

import com.Springboot.aha.DTO.MessageResponse;
import com.Springboot.aha.Entity.Category;
import com.Springboot.aha.Entity.Item;
import com.Springboot.aha.Exception.User.UnauthorizedException;
import com.Springboot.aha.Security.JwtUtils;
import com.Springboot.aha.Service.IItemService;
import com.Springboot.aha.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@CrossOrigin
@RestController
@RequestMapping("/api/items")
public class ItemAPI {

    @Autowired
    private IItemService itemService;

    @Autowired
    private IUserService userService;

    @Autowired
    private JwtUtils jwtUtils;

    /* Get token from resquest for authentication */
    private String getAuthToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        return authorizationHeader.substring(JwtUtils.Bearer.length());
    }

    @PostMapping(value = "/add")
    public ResponseEntity<?> createItem(HttpServletRequest request, @Valid @RequestBody Item model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errors = bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.joining(" ,"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse(errors));
        } else {
            String token = getAuthToken(request);
            int authUserId = jwtUtils.getId(token);
            model.setUser(userService.getUserById(authUserId));

            return ResponseEntity.ok(itemService.save(model));
        }
    }

    @GetMapping("/get")
    public List<Item> getItems(HttpServletRequest request) {
        String token = getAuthToken(request);
        int authUserId = jwtUtils.getId(token);
        return itemService.findByAccount(authUserId);
    }

    @PutMapping(value = "/update/{id}")
    public Item editITem(@RequestBody Item model, @PathVariable("id") int id, HttpServletRequest request) {
        return itemService.update(model, id,request);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable("id") int id, HttpServletRequest request) {
        return ResponseEntity.ok(itemService.remove(id,request));
    }

    @GetMapping("/getByAccId")
    public List<Item> getItemsByAccountId(@RequestParam("id") int id) {
        return itemService.findByAccount(id);
    }

    @GetMapping("/getByCate")
    public List<Item> getItemsByCate(@RequestBody Category category) {
        return itemService.findItemByCategory(category);
    }

    @GetMapping("/get-by-price")
    public List<Item> getItemsByPrice(@RequestParam("price") int price) {
        return itemService.findByPrice(price);
    }
}

