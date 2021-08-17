package com.Springboot.aha.Service;

import com.Springboot.aha.Entity.ITemEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IItemService {
    ITemEntity save(ITemEntity item);
    ITemEntity update(ITemEntity item);
    int delete(int id);
    List<ITemEntity> findAll();
    List<ITemEntity> findByAccount(int id);


}
