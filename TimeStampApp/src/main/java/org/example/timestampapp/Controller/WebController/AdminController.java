package org.example.timestampapp.Controller.WebController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin/main")
    public String main() {
        return "admin";
    }
}
