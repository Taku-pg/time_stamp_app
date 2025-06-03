package org.example.timestampapp.Service;

import org.example.timestampapp.Model.DTO.UserDTO;
import org.example.timestampapp.Model.Entity.User;
import org.example.timestampapp.Model.Repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final UserService userService;
    public LoginService(UserService userService) {
        this.userService = userService;
    }

    public String checkRole(UserDTO userDTO) {
        System.out.println("call checkRole");
        User user=userService.getUserByUsername(userDTO.getUsername()).orElse(null);
        if(user==null) {
            return "Invalid username or password";
        }
        return user.getRole();
    }
}
