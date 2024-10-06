package com.example.AtomWallet.service.impl;

import com.example.AtomWallet.dto.WalletDto;
import com.example.AtomWallet.exeption.AccountNumberNotFoundException;
import com.example.AtomWallet.exeption.InsufficientFundsException;
import com.example.AtomWallet.model.Transaction;
import com.example.AtomWallet.model.Wallet;
import com.example.AtomWallet.model.enumeration.TransactionType;
import com.example.AtomWallet.repository.TransactionsRepository;
import com.example.AtomWallet.repository.WalletRepository;
import com.example.AtomWallet.service.TransactionsService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TransactionsServiceImpl implements TransactionsService {

    @Autowired
    private WalletRepository walletRepository;

    @Override
    public String depositAmount(String accountNumber, BigDecimal amount) throws AccountNotFoundException {
        Wallet wallet = this.walletRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException(accountNumber));

        BigDecimal newBalance = wallet.getBalance().add(amount);
        wallet.setBalance(newBalance);

        this.walletRepository.save(wallet);

        return "Deposit successful. New balance: " + newBalance.toString();
    }


    @Override
    @Transactional
    public String withdrawAmount(String accountNumber, double amount) {
        Wallet wallet = this.walletRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNumberNotFoundException(accountNumber));

        if (wallet.getBalance().compareTo(BigDecimal.valueOf(amount)) < 0) {
            throw new InsufficientFundsException("Insufficient funds for withdrawal.");
        }

        BigDecimal newBalance = wallet.getBalance().subtract(new BigDecimal(amount));
        wallet.setBalance(newBalance);
        this.walletRepository.save(wallet);
        return "Withdraw successful. New balance: " + newBalance.toString();
    }

}