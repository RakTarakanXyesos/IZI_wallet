package com.example.AtomWallet.controller;

import com.example.AtomWallet.dto.WalletDto;
import com.example.AtomWallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @GetMapping
    public List<WalletDto> getWallet() {
      return this.walletService.getWallet();
    }

    @GetMapping("/{id}")
    public WalletDto getWalletById(@RequestParam Long id) {
        return this.walletService.getWalletById(id);
    }

    @PostMapping
    public WalletDto createWallet(@RequestBody WalletDto walletDto) {
        return this.walletService.createWallet(walletDto);
    }

    @PutMapping("/{id}")
    public WalletDto updateWallet(@PathVariable Long id, @RequestBody WalletDto walletDto) {
        return this.walletService.updateWallet(id, walletDto);
    }

    @DeleteMapping("/{id}")
    public void deleteWallet(@PathVariable Long id) {
        this.walletService.deleteWallet(id);
    }

}
