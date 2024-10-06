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

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestParam String fromAccountNumber,
                                           @RequestParam String toAccountNumber,
                                           @RequestParam BigDecimal amount) {
        try {
            transactionsService.transferAmount(fromAccountNumber, toAccountNumber, amount);
            return ResponseEntity.status(HttpStatus.OK).body("Transfer successful");
        } catch (AccountNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (InsufficientFundsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}