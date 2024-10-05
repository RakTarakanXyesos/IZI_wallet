package com.example.AtomWallet.controller;

import com.example.AtomWallet.dto.UserDto;
import com.example.AtomWallet.model.User;
import com.example.AtomWallet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserDto> getUser() {
        return this.userService.getAllUser();
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return this.userService.getUserById(id);
    }

    @PostMapping
    public UserDto createUser(@RequestBody UserDto userDto) {
        return this.userService.save(userDto);
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        return this.userService.update(id, userDto);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        this.userService.delete(id);
    }

}
