package com.example.AtomWallet.dto;

import com.example.AtomWallet.model.User;
import com.fasterxml.jackson.databind.util.BeanUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.beans.BeanUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String address;
    private Long userId;


    public UserDto(User user) {
        BeanUtils.copyProperties(user, this);
    }
}
