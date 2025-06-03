package org.example.timestampapp.Service;

import org.example.timestampapp.Model.Entity.User;
import org.example.timestampapp.Model.Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        System.out.println("Change password");
        String encodedCurrentPassword = passwordEncoder.encode(currentPassword);
        System.out.println("hashed currPass: "+encodedCurrentPassword);
        User user= userRepository.findUserByUsername(username).orElse(null);

        if(user==null) {
            System.out.println("user not found");
            throw new RuntimeException("User not found");
        }
        if(passwordEncoder.matches(user.getPassword(),encodedCurrentPassword)) {
            System.out.println("wrong password");
            throw new RuntimeException("Current password does not match");
        }
        String encodedNewPassword = passwordEncoder.encode(newPassword);
        System.out.println("hashed newPass: "+encodedNewPassword);
        user.setPassword(encodedNewPassword);
        userRepository.save(user);
    }
}
