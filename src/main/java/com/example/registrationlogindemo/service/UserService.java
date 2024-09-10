package com.example.registrationlogindemo.service;

import com.example.registrationlogindemo.dto.UserDto;
import com.example.registrationlogindemo.entity.User;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void saveUser(UserDto userDto);

    User findByEmail(String email);
    List<UserDto> findAllUsers();

	UserDto findById(Long id);

	void updateUser(UserDto userDto);

	boolean deleteById(long id);
}
