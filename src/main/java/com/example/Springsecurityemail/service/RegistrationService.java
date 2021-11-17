package com.example.Springsecurityemail.service;

import com.example.Springsecurityemail.dto.RegistrationDto;
import com.example.Springsecurityemail.model.ConfirmationToken;
import com.example.Springsecurityemail.model.User;
import com.example.Springsecurityemail.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class RegistrationService {

    @Autowired
    private UserService userService;

    @Autowired
    private ConfirmationTokenService confirmationTokenService;

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

    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService.getToken(token)
                .orElseThrow(() -> new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiredAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        userService.enableUser(confirmationToken.getUser().getEmail());
        return "confirmed";
    }
}
