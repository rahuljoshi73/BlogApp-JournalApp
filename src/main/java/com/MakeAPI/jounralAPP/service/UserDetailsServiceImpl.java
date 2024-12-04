package com.MakeAPI.jounralAPP.service;

import com.MakeAPI.jounralAPP.entity.User;
import com.MakeAPI.jounralAPP.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUserName(username);
        System.out.println(user);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return buildUserDetails(user);
    }

    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepo.findByEmail(email);

        // Check if the user is present in the Optional container
        User user = userOptional.orElseThrow(() ->
                new UsernameNotFoundException("User not found with email: " + email)
        );

        return buildUserDetails(user); // Pass the extracted User object to buildUserDetails
    }

    private UserDetails buildUserDetails(User user) {
        List<String> authorities = new ArrayList<>(user.getRoles());
        authorities.add("EMAIL:" + user.getEmail());// Include email as an authority

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername()) // Keep the username as the principal
                .password(user.getPassword())
                .authorities(authorities.toArray(new String[0]))
                .build();
    }


}