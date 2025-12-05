package com.revature.ExpenseReport.controllers;

import com.revature.ExpenseReport.Repository.AppUserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    //fields
    


    //constructor
    public AuthController(AppUserRepo appUserRepo, PasswordEncoder passwordEncoder, JwtUtil jwtUtil){

    }

    //methods
}
