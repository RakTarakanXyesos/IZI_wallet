package com.example.AtomWallet.controller;

import com.example.AtomWallet.exeption.InsufficientFundsException;
import com.example.AtomWallet.service.TransactionsService;
import com.example.AtomWallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/wallets")
public class TransactionsController {

    @Autowired
    private TransactionsService transactionsService;

    @Autowired
    private WalletService walletService;

    @PostMapping("/deposit/{accNo}")
    public ResponseEntity<String> deposit(@PathVariable("accNo") String accountNumber, @RequestParam BigDecimal amount) throws AccountNotFoundException {
        String response = this.transactionsService.depositAmount(accountNumber, amount);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/withdraw/{accNo}")
    public ResponseEntity<String> withdraw(@PathVariable("accNo") String accountNumber, @RequestParam double amount) throws AccountNotFoundException {
        String response = this.transactionsService.withdrawAmount(accountNumber, amount);
        return ResponseEntity.ok(response);
    }
}