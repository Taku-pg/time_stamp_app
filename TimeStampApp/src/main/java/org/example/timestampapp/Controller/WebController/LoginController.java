package org.example.timestampapp.Controller.WebController;

import org.example.timestampapp.Model.DTO.UserDTO;
import org.example.timestampapp.Service.EmployeeService;
import org.example.timestampapp.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/login")
    public String loginCheck(@ModelAttribute("user") UserDTO user, Model model) {
        String role=loginService.checkRole(user);
        if(role.equals("ADMIN"))
            return "redirect:/admin/main";
        else if(role.equals("EMPLOYEE"))
            return "redirect:/employee/main";
        else
            return "redirect:/login";
    }
}
