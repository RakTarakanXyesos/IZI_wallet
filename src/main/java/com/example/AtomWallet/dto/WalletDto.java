package com.example.AtomWallet.dto;

import com.example.AtomWallet.model.User;
import com.example.AtomWallet.model.Wallet;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WalletDto {

    private Long id;
    private String accountNumber;
    private String currency;
    private Long userId;
    private BigDecimal balance = BigDecimal.ZERO;
    private LocalDateTime createdAt;

    public WalletDto(Wallet walet) {
        BeanUtils.copyProperties(walet, this);
    }
}
