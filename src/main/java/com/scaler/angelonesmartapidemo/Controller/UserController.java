package com.scaler.angelonesmartapidemo.Controller;

import com.scaler.angelonesmartapidemo.Service.UserService;
import com.scaler.angelonesmartapidemo.dtos.UserInfoRequestDto;
import com.scaler.angelonesmartapidemo.dtos.LoginRequestDTO;
import com.scaler.angelonesmartapidemo.dtos.LoginResponseDTO;
import com.scaler.angelonesmartapidemo.dtos.UserInfoResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public LoginResponseDTO login(
            @RequestBody LoginRequestDTO loginRequestDto,
            @RequestParam String privateKey) throws Exception {
        return userService.login(
                loginRequestDto.getClientcode(),
                loginRequestDto.getPassword(),
                loginRequestDto.getTotp(),
                privateKey
        );
    }


    @GetMapping("/userInfo")
    public UserInfoResponseDto getUserInfo(@RequestBody UserInfoRequestDto userInfoRequestDto,@RequestParam String privateKey) throws Exception {
        return  userService.getUserInfo(userInfoRequestDto.getToken(),privateKey);
    }
}

