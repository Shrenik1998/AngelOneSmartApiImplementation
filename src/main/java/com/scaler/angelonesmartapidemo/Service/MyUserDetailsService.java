package com.scaler.angelonesmartapidemo.Service;


import com.scaler.angelonesmartapidemo.models.UserPrinciple;
import com.scaler.angelonesmartapidemo.models.Users;
import com.scaler.angelonesmartapidemo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users user1 = userRepo.findByUserName(username);

        if (user1 == null)
        {
            throw new UsernameNotFoundException("User not found");
        }

        return new UserPrinciple(user1);
    }
}

