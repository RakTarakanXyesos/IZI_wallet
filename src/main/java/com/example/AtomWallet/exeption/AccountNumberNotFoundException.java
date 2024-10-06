package com.example.AtomWallet.exeption;

public class AccountNumberNotFoundException extends RuntimeException {
    public AccountNumberNotFoundException(String message) {
        super(message);
    }
}
