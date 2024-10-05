package com.example.AtomWallet.service.impl;

import com.example.AtomWallet.dto.UserDto;
import com.example.AtomWallet.exeption.UserNotFoundException;
import com.example.AtomWallet.model.User;
import com.example.AtomWallet.repository.UserRepository;
import com.example.AtomWallet.service.UserService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

@Override
    public List<UserDto> getAllUser() {
        this.userRepository.findAll();
        return null;
    }

    @Override
    public UserDto getUserById(Long id) {
        // Використовуємо Optional для отримання користувача
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));

        return new UserDto(user);
    }

    @Override
    public UserDto save(UserDto userDto) {
    User user = this.dtoToEntity(userDto);
    User saveUser = this.userRepository.save(user);
    return new UserDto(saveUser);

    }
    
    @Override
    public UserDto update(Long id, UserDto userDto) {
    User user = this.userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));

    user.setName(userDto.getName());
    user.setEmail(userDto.getEmail());
    user.setConatact_number(userDto.getConatact_number());
    user.setAddress(userDto.getAddress());
    this.userRepository.save(user);
    return new UserDto(user);
    }

    @Override
    public void delete(Long id) {
    User user = this.userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
    this.userRepository.delete(user);
    }

    private User dtoToEntity(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        return user;
    }
    
}
