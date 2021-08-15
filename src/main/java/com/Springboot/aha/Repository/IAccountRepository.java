package com.Springboot.aha.Repository;

import com.Springboot.aha.Entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountRepository extends JpaRepository<AccountEntity,Integer> {
}
