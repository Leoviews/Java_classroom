package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @PostMapping("/{accountId}")
    public Transaction addTransaction(
            @PathVariable Long accountId,
            @RequestParam Double amount,
            @RequestParam String transactionType,
            @RequestParam Date transactionDate) {

        return service.createTransaction(accountId, amount, transactionType, transactionDate);
    }

    @GetMapping("/{accountId}")
    public List<Transaction> getTransactions(@PathVariable Long accountId) {
        return service.getTransactionsByAccountId(accountId);
    }
}

