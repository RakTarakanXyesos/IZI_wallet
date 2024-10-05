package com.example.AtomWallet.service;

import com.example.AtomWallet.dto.UserDto;
import com.example.AtomWallet.model.User;

import java.util.List;

public interface UserService {
    public List<UserDto> getAllUser();
    public UserDto getUserById(Long id);
    public UserDto save(UserDto userDto);
    public UserDto update(Long id, UserDto userDto);
    public void delete(Long id);
}
