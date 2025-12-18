package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.Account;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountId(Long accountId);


}

