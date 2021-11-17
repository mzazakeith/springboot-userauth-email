package com.example.Springsecurityemail.controller;

import com.example.Springsecurityemail.dto.RegistrationDto;
import com.example.Springsecurityemail.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/registration")
@AllArgsConstructor
public class RegistrationController {

    private RegistrationService registrationService;

    @PostMapping
    public String register(@Valid @RequestBody RegistrationDto registrationDto){
        return registrationService.register(registrationDto);
    }

    @GetMapping("/confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }
}
