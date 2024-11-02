package com.scaler.angelonesmartapidemo.Controller;

import com.scaler.angelonesmartapidemo.dtos.LoginDto;
import com.scaler.angelonesmartapidemo.dtos.UserDto;
import com.scaler.angelonesmartapidemo.models.Users;
import com.scaler.angelonesmartapidemo.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public String hello(HttpServletRequest request)
    {
        return "hello " + request.getSession().getId();
    }

    @PostMapping("/register")
    public String register(@RequestBody UserDto userDto){
        return userService.register(userDto);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDto loginDto)
    {
        return userService.verify(loginDto);
    }
}

