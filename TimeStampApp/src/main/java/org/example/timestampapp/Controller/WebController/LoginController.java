package org.example.timestampapp.Controller.WebController;

import org.example.timestampapp.Model.DTO.UserDTO;
import org.example.timestampapp.Service.EmployeeService;
import org.example.timestampapp.Service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    private final LoginService loginService;
    private final EmployeeService employeeService;

    public LoginController(LoginService loginService,
                           EmployeeService employeeService) {
        this.loginService = loginService;
        this.employeeService = employeeService;
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user",new UserDTO());
        return "login";
    }

}
