package com.example.Springsecurityemail.service;

import com.example.Springsecurityemail.dto.RegistrationDto;
import com.example.Springsecurityemail.model.User;
import com.example.Springsecurityemail.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    @Autowired
    private UserService userService;

    public String register(RegistrationDto registrationDto) {
        return userService.signUpUser(
                new User(
                        registrationDto.getFirstName(),
                        registrationDto.getLastName(),
                        registrationDto.getEmail(),
                        registrationDto.getPassword(),
                        UserRole.USER
                )
        );
    }
}
