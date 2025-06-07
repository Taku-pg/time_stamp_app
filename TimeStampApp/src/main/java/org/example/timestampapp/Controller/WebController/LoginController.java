package org.example.timestampapp.Controller.WebController;

import org.example.timestampapp.Model.DTO.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user",new UserDTO());
        return "login";
    }

}
