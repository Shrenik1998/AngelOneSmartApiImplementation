package com.scaler.angelonesmartapidemo.Service;

import com.scaler.angelonesmartapidemo.dtos.LoginDto;
import com.scaler.angelonesmartapidemo.dtos.UserDto;
import com.scaler.angelonesmartapidemo.models.Users;
import com.scaler.angelonesmartapidemo.repo.UserRepo;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.task.ThreadPoolTaskSchedulerBuilder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private ThreadPoolTaskSchedulerBuilder threadPoolTaskSchedulerBuilder;

    @Autowired
    private JWTService jwtService;

    public String register(UserDto userDto)
    {

        Users user = userRepo.findByUserName(userDto.getUserName());

        if(user == null)
        {
            user = new Users();
            //        user.setId(userDto.getId());
            user.setUserName(userDto.getUserName());
            user.setPassword(userDto.getPassword());
            user.setRole(userDto.getRole());
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            try{
                userRepo.save(user);
                return "User created successfully";
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }

        }

        return "User already exists";
    }

    public String verify(LoginDto userDto)
    {
        Users user = new Users();
//        user.setId(userDto.getId());
        user.setUserName(userDto.getUserName());
        user.setPassword(userDto.getPassword());
//        user.setRole(userDto.getRole());

        Authentication authenticaion = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword()));

        if(authenticaion.isAuthenticated())
        {
            return jwtService.generateToken(user.getUserName());
        }

        return "FAIL";
    }
}
