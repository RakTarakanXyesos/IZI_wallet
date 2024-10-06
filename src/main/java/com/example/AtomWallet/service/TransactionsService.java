package com.example.AtomWallet.service;

import com.example.AtomWallet.dto.WalletDto;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;

public interface TransactionsService {
      String depositAmount(String accountNumber, BigDecimal amount) throws AccountNotFoundException;

      String withdrawAmount(String accountNumber, double amount);
}
