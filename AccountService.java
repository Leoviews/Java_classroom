package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.Account;
import com.example.demo.AccountRepository;

@Service
public class AccountService {

    private final AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public Account createAccount(Account account) {
        return repository.save(account);
    }

    public List<Account> getAllAccounts() {
        return repository.findAll();
    }
    public Account getAccountById(Long accountId) {
        return repository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }
    public Map<String, String> getAccountTypeAndNumber(Long accountId) {
        Account account = getAccountById(accountId);

        Map<String, String> details = new HashMap<>();
        details.put("accountNumber", account.getAccountNumber());
        details.put("accountType", account.getAccountType());

        return details;
    }
    public Account deposit(Long id, double amount) {
        Account account = repository.findById(id).orElseThrow();
        account.setBalance(account.getBalance() + amount);
        return repository.save(account);
    }

    public Account withdraw(Long id, double amount) {
        Account account = repository.findById(id).orElseThrow();

        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        account.setBalance(account.getBalance() - amount);
        return repository.save(account);
    }
    public void deleteAccount(Long accountId) {
        if (!repository.existsById(accountId)) {
            throw new RuntimeException("Account not found");
        }
        repository.deleteById(accountId);
    }


}
