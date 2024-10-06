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
import com.fasterxml.jackson.databind.JavaType;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TransactionsServiceImpl implements TransactionsService {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private TransactionsRepository transactionsRepository;

    @Override
    public String depositAmount(String accountNumber, BigDecimal amount) throws AccountNotFoundException {
        Wallet wallet = this.walletRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException(accountNumber));

        BigDecimal newBalance = wallet.getBalance().add(amount);
        wallet.setBalance(newBalance);
        this.walletRepository.save(wallet);

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setAccountNumber(accountNumber);
        transaction.setTransactionType(TransactionType.deposit.toString());
        transaction.setCreatedAt(LocalDateTime.now());
        transaction.setWalletId(wallet.getId());
        transaction.setWallet(wallet);
        this.transactionsRepository.save(transaction);

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

        BigDecimal newBalances = wallet.getBalance().subtract(new BigDecimal(amount));
        wallet.setBalance(newBalances);
        this.walletRepository.save(wallet);

        Transaction transactions = new Transaction();
        transactions.setAmount(BigDecimal.valueOf(amount));
        transactions.setAccountNumber(accountNumber);
        transactions.setTransactionType(TransactionType.withdrawal.toString());
        transactions.setCreatedAt(LocalDateTime.now());
        transactions.setWalletId(wallet.getId());
        transactions.setWallet(wallet);
        this.transactionsRepository.save(transactions);
        return "Withdraw successful. New balance: " + newBalances.toString();
    }

    @Override
    @Transactional
    public String transferAmount(String fromAccountNumber, String toAccountNumber, BigDecimal amount)
            throws AccountNotFoundException, InsufficientFundsException {

        Wallet fromWallet = getWallet(fromAccountNumber);
        Wallet toWallet = getWallet(toAccountNumber);

        checkSufficientFunds(fromWallet, amount);

        updateWalletBalances(fromWallet, toWallet, amount);

        return "Transfer successful";
    }

    private Wallet getWallet(String accountNumber) throws AccountNotFoundException {
        return this.walletRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException(accountNumber));
    }

    private void checkSufficientFunds(Wallet wallet, BigDecimal amount) throws InsufficientFundsException {
        if (wallet.getBalance().compareTo(amount) < 0) {
            throw new InsufficientFundsException(wallet.getAccountNumber());
        }
    }

    private void updateWalletBalances(Wallet fromWallet, Wallet toWallet, BigDecimal amount) {
        if (fromWallet.getBalance().compareTo(amount) < 0) {
            throw new InsufficientFundsException("Недостатньо коштів на рахунку.");
        }

        fromWallet.setBalance(fromWallet.getBalance().subtract(amount));
        toWallet.setBalance(toWallet.getBalance().add(amount));

        this.walletRepository.save(fromWallet);
        this.walletRepository.save(toWallet);

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setWallet(toWallet); // Переконайтеся, що toWallet не null
        transaction.setFromAccountNumber(fromWallet.getAccountNumber());
        transaction.setToAccountNumber(toWallet.getAccountNumber());
        transaction.setTransactionType(TransactionType.transfer.toString());
        transaction.setCreatedAt(LocalDateTime.now());

        if (transaction.getWallet() == null) {
            throw new IllegalArgumentException("Гаманець для транзакції не може бути null.");
        }

        this.transactionsRepository.save(transaction);
    }
}