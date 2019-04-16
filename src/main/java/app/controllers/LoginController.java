package app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path = "/login")
public class LoginController {
    @RequestMapping(method = RequestMethod.GET)
    public String getLoginPage() {
        return "login";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String login() {
        return "redirect:/home";
    }
}
