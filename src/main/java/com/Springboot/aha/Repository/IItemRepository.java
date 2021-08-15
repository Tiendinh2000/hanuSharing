package com.Springboot.aha.Repository;

import com.Springboot.aha.Entity.ITemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.event.ItemEvent;

public interface IItemRepository extends JpaRepository<ITemEntity,Integer> {
}
