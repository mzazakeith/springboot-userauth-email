package com.example.Springsecurityemail.controller;

import com.example.Springsecurityemail.dto.RegistrationDto;
import com.example.Springsecurityemail.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/registration")
@AllArgsConstructor
public class RegistrationController {

    private RegistrationService registrationService;

    public String register(@RequestBody RegistrationDto registrationDto){
        return registrationService.register(registrationDto);
    }
}
