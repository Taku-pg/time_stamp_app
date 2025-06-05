package org.example.timestampapp.Service;

import org.example.timestampapp.Model.Entity.User;
import org.example.timestampapp.Model.Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Transactional
    public void changePassword(String username,String currentPassword, String newPassword) {
        //String encodedCurrentPassword = passwordEncoder.encode(currentPassword);
        User user= userRepository.findUserByUsername(username).orElse(null);


        if(user==null) {
            throw new NoSuchElementException("User not found");
        }
        System.out.println(currentPassword);
        System.out.println(user.getPassword());
        if(!passwordEncoder.matches(currentPassword,user.getPassword())) {
            throw new NoSuchElementException("Current password does not match");
        }
        String encodedNewPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedNewPassword);
        userRepository.save(user);
    }
}
