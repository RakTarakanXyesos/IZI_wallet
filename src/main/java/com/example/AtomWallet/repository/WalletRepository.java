package com.example.AtomWallet.repository;

import com.example.AtomWallet.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    @Query("SELECT w FROM Wallet w WHERE w.accountNumber = :accountNumber")
    Optional<Wallet> findByAccountNumber(String accountNumber);
}
