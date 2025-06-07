package org.example.timestampapp.Service;

import org.example.timestampapp.Model.Entity.User;
import org.example.timestampapp.Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.InputMismatchException;
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
        User user= userRepository
                .findUserByUsername(username)
                .orElseThrow(()->new NoSuchElementException("No user found for username " + username));

        if(!passwordEncoder.matches(currentPassword,user.getPassword())) {
            throw new InputMismatchException("Current password does not match");
        }

        String encodedNewPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedNewPassword);
        userRepository.save(user);
    }
}
