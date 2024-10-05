package com.example.AtomWallet.service;

import com.example.AtomWallet.dto.WalletDto;

import java.util.List;

public interface WalletService {

    public List<WalletDto> getWallet();
    public WalletDto getWalletById(Long id);
    public WalletDto createWallet(WalletDto walletDto);
    public WalletDto updateWallet(Long id, WalletDto walletDto);
    public void deleteWallet(Long id);
}
