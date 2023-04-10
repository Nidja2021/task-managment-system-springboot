package com.taskmanagmentsystem.auth;

import com.taskmanagmentsystem.auth.dto.LoginDto;
import com.taskmanagmentsystem.auth.dto.RegisterDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @PostMapping("/register")
    public String register(@RequestBody RegisterDto registerDto){
        return registerDto.username();
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDto loginDto){
        return loginDto.username();
    }

    @GetMapping("/logout")
    public String logout(){
        return "Logout";
    }

}
