package com.example.AtomWallet.service.impl;

import com.example.AtomWallet.dto.WalletDto;
import com.example.AtomWallet.model.User;
import com.example.AtomWallet.model.Wallet;
import com.example.AtomWallet.repository.UserRepository;
import com.example.AtomWallet.repository.WalletRepository;
import com.example.AtomWallet.service.WalletService;
import com.fasterxml.jackson.databind.util.BeanUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<WalletDto> getWallet() {
        this.walletRepository.findAll();
        return null;
    }

    @Override
    public WalletDto getWalletById(Long id) {
        Wallet wallet = this.walletRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Wallet with id " + id + " not found"));
        return new WalletDto(wallet);
    }

    @Override
    public WalletDto updateWallet(Long id, WalletDto walletDto) {
        Wallet wallet = this.walletRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Wallet with id " + id + " not found"));

        wallet.setBalance(wallet.getBalance());
        wallet.setAccountNumber(wallet.getAccountNumber());
        wallet.setCurrency(wallet.getCurrency());
        this.walletRepository.save(wallet);
        return new WalletDto(wallet);
    }

    @Transactional
    @Override
    public WalletDto createWallet(WalletDto walletDto) {
        if (walletDto.getUserId() == null) {
            throw new IllegalArgumentException("User ID must not be null");
        }

        User user = userRepository.findById(walletDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Wallet wallet = this.dtoToEntity(walletDto);
        wallet.setUser(user);

        Wallet savedWallet = this.walletRepository.save(wallet);
        return new WalletDto(savedWallet);
    }

    @Override
    public void deleteWallet(Long id) {
        this.walletRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Wallet with id " + id + " not found"));
        this.walletRepository.deleteById(id);
    }


    private Wallet dtoToEntity(WalletDto walletDto) {
        Wallet wallet = new Wallet();
        wallet.setBalance(walletDto.getBalance());
        wallet.setAccountNumber(walletDto.getAccountNumber());
        wallet.setCurrency(walletDto.getCurrency());
        return wallet;
    }
}