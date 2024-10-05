package com.example.AtomWallet.exeption;

public class WalletNotFoundExeption extends RuntimeException {
    public WalletNotFoundExeption(String message) {
        super(message);
    }
}
