package app.controllers;

import app.entities.Profile;
import app.services.AccountDetails;
import app.services.AccountService;
import app.services.ContactService;
import app.services.ProfileService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(path = "/network")
public class NetworkController {
    private final AccountService accountService;
    private final ProfileService profileService;
    private final ContactService contactService;

    public NetworkController(AccountService accountService, ProfileService profileService, ContactService contactService) {
        this.accountService = accountService;
        this.profileService = profileService;
        this.contactService = contactService;
    }

    @GetMapping
    public String getNetworkPage(ModelMap modelMap, @AuthenticationPrincipal AccountDetails account) {
        final List<Profile> circle1 = contactService.circleOfProfile(account.getUser().getId()).apply(1);
        modelMap.put("contacts", circle1);
        return "network";
    }
}
