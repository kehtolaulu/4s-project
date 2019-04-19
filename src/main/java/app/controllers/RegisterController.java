package app.controllers;

import app.forms.CompanyForm;
import app.forms.ProfileForm;
import app.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/register")
public class RegisterController {
    @Autowired
    private RegistrationService registrationService;

    @GetMapping
    public String getRegisterPage() {
        return "register";
    }

    @PostMapping("/company")
    public String register(CompanyForm registerForm) {
        registrationService.register(registerForm);
        return "redirect:/home";
    }

    @PostMapping("/profile")
    public String register(ProfileForm registerForm) {
        registrationService.register(registerForm);
        return "redirect:/home";
    }
}
