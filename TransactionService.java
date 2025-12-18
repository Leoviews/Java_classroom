package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepo;
    private final AccountRepository service;

    public TransactionService(TransactionRepository transactionRepo, AccountRepository service) {
        this.transactionRepo = transactionRepo;
        this.service = service;
    }

    // Create Transaction (connected to Account)
    public Transaction createTransaction(Long accountId, Double amount,
                                         String transactionType, Date transactionDate) {

        Account account = service.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setTransactionType(transactionType);
        transaction.setTransactionDate(transactionDate);
        transaction.setAccount(account);

        // Balance update logic
        if (transactionType.equalsIgnoreCase("Credit")) {
            account.setBalance(account.getBalance() + amount);
        } else {
            account.setBalance(account.getBalance() - amount);
        }

        service.save(account);
        return transactionRepo.save(transaction);
    }

    // Get all transactions for an account
    public List<Transaction> getTransactionsByAccountId(Long accountId) {
        return transactionRepo.findByAccount_AccountId(accountId);
    }
}

