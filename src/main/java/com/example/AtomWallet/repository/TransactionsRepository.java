package com.example.AtomWallet.repository;

import com.example.AtomWallet.model.Transaction;
import com.example.AtomWallet.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionsRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByWalletAccountNumber(String accountNumber);
}
