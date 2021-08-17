package com.Springboot.aha.Repository;

import com.Springboot.aha.Entity.AccountEntity;
import com.Springboot.aha.Entity.ITemEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.event.ItemEvent;
import java.util.List;

public interface IItemRepository extends JpaRepository<ITemEntity,Integer> {
     List<ITemEntity> findByAccount(AccountEntity accountDTO);

}
