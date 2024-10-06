package com.example.AtomWallet.dto;

import com.example.AtomWallet.model.Transaction;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionsDto {

    private Long id;
    private Long walletId;
    private Double amount;
    private String transactionType;
    private LocalDateTime createdAt;

    public TransactionsDto(Transaction transaction) {
        BeanUtils.copyProperties(transaction, this);
    }
}
