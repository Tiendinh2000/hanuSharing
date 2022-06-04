package com.Springboot.aha.Service.impl;

import com.Springboot.aha.Entity.Category;
import com.Springboot.aha.Entity.Item;
import com.Springboot.aha.Entity.User;
import com.Springboot.aha.Exception.common.BadRequestException;
import com.Springboot.aha.Exception.User.UnauthorizedException;
import com.Springboot.aha.Repository.IItemRepository;
import com.Springboot.aha.Repository.IUserRepository;
import com.Springboot.aha.Security.JwtUtils;
import com.Springboot.aha.Service.IItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService implements IItemService {

    @Autowired
    private IItemRepository itemRepository;

    @Autowired
    private IUserRepository accountRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public Item save(Item item) {
        return itemRepository.save(item);
    }

    /* Get token from resquest for authentication */
    private String getAuthToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        return authorizationHeader.substring(JwtUtils.Bearer.length());
    }

    @Override
    public Item update(Item newItem, int id, HttpServletRequest request) {
        int authUserId = jwtUtils.getId(getAuthToken(request));
        if (itemRepository.existsById(id)) {
            Item edittingItem = itemRepository.findById(id).get();
            if (edittingItem.getUser().getUser_id() != authUserId)
                throw new UnauthorizedException("you're just permitted edit your own items");
            if (newItem.getName() != null)
                edittingItem.setName(newItem.getName());
            if (newItem.getPrice() != edittingItem.getPrice())
                edittingItem.setPrice(newItem.getPrice());
            return itemRepository.save(edittingItem);
        }
        throw new BadRequestException("Bad Request");
    }

    @Override
    public Item remove(int itemId, HttpServletRequest request) {
        int authUserId = jwtUtils.getId(getAuthToken(request));
        try {
            Item item = itemRepository.findById(itemId).get();
            if (item.getUser().getUser_id() != authUserId) {
                throw new UnauthorizedException("you're just permitted delete your own items");
            } else {
                itemRepository.delete(item);
                return item;
            }
        } catch (Exception e) {
            throw new BadRequestException("Bad Request!!");
        }
    }


    @Override
    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    @Override
    public Item findById(int id) {
        try {
            return itemRepository.findById(id).get();
        } catch (Exception e) {

            return null;
        }
    }

    @Override
    public List<Item> findByAccount(int id) {
        if (accountRepository.existsById(id)) {
            User account = accountRepository.findById(id).get();
            return itemRepository.findByUser(account);
        } else
            return null;
    }

    @Override
    public List<Item> findItemByCategory(Category category) {
        return itemRepository.findAllByCategory(category);
    }

    @Override
    public List<Item> findByPrice(int price) {
        return itemRepository.findByPrice(price);
    }


}