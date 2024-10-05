package com.example.AtomWallet.repository;

import com.example.AtomWallet.dto.UserDto;
import com.example.AtomWallet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
