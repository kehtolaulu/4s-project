package app.controllers;

import app.entities.*;
import app.exceptions.AccountNotFoundException;
import app.exceptions.CouldntAddContactException;
import app.forms.EducationForm;
import app.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/profile")
public class ProfileController {
    private final ProfileService profileService;
    private final EducationService educationService;
    private final AccountService accountService;
    private final ContactService contactService;

    @Autowired
    public ProfileController(ProfileService profileService, EducationService educationService, AccountService accountService, ContactService contactService) {
        this.profileService = profileService;
        this.educationService = educationService;
        this.accountService = accountService;
        this.contactService = contactService;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public String getProfileByIdPage(@PathVariable Long id,
                                     ModelMap modelMap,
                                     @AuthenticationPrincipal AccountDetails account) {
        final Account profile = accountService.accountById(id).orElseThrow(AccountNotFoundException::new);
        final Account user = profileService.findAccountById(account.getUser().getId())
                .orElseThrow(AccountNotFoundException::new);
        final List<Profile> contacts = contactService.circleOfProfile(user.getId()).apply(1);
        modelMap.put("profile", profile);
        modelMap.put("user", user);
        modelMap.put("contacts", contacts);
        return "profile";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getProfilePage(@AuthenticationPrincipal AccountDetails account, ModelMap modelMap) {
        final Account user = account.getUser();
        final Profile profile = profileService.findById(user.getId())
                .orElseThrow(AccountNotFoundException::new);
        modelMap.put("profile", profile);
        modelMap.put("user", profile);
        return "profile";
    }

    @RequestMapping(path = "/{id}/edit", method = RequestMethod.POST)
    public String editStatus(@AuthenticationPrincipal AccountDetails account,
                             String status,
                             String location,
                             @PathVariable long id) {
        final Profile profile = profileService.findById(account.getUser().getId())
                .orElseThrow(AccountNotFoundException::new);
        assert profile.getId() == id;

        String newStatus = "";
        if (status != null) {
            switch (Integer.parseInt(status)) {
                case 1:
                    newStatus = "Actively applying";
                    break;
                case 2:
                    newStatus = "Casually looking";
                    break;
                case 3:
                    newStatus = "Open to offers";
                    break;
                case 4:
                    newStatus = "Not open to offers";
                    break;
            }
        } else {
            newStatus = profile.getStatus();
        }
        profile.setStatus(newStatus);
        profile.setLocation(location);
        profileService.save(profile);
        return "redirect:/profile";
    }

    @PostMapping(path = "/{id}/add_skill")
    public String addSkill(@AuthenticationPrincipal AccountDetails account,
                           String skillName,
                           @PathVariable long id) {
        Skill skill = profileService.createSkill(skillName);

        final Profile profile = profileService.findById(account.getUser().getId())
                .orElseThrow(AccountNotFoundException::new);
        assert profile.getId() == id;

        profileService.add(skill).to(profile);
        return "redirect:/profile";
    }

    @PostMapping(path = "/{id}/add_edu")
    public String addEducation(@AuthenticationPrincipal AccountDetails account,
                               EducationForm educationForm,
                               @PathVariable long id) {
        final Profile profile = profileService.findById(account.getUser().getId())
                .orElseThrow(AccountNotFoundException::new);
        assert profile.getId() == id;
        educationService.save(educationForm, profile);
        return "redirect:/profile";
    }

    @PostMapping(path = "/{id}/add_work")
    public String addWorkExperience(@AuthenticationPrincipal AccountDetails account,
                                    WorkExperience workExperience,
                                    @PathVariable long id) {
        final Profile profile = profileService.findById(account.getUser().getId())
                .orElseThrow(AccountNotFoundException::new);
        assert profile.getId() == id;
        workExperience.setId(null);
        profileService.add(workExperience).to(profile);
        return "redirect:/profile";
    }

    @PostMapping(path = "/{id}/add")
    public String addToContacts(@AuthenticationPrincipal AccountDetails account, @PathVariable long id) throws CouldntAddContactException {
        if (account.getUser().getId() == id) {
            throw new CouldntAddContactException();
        }
        final Profile myProfile = profileService.findById(account.getUser().getId()).orElseThrow(AccountNotFoundException::new);
        final Profile profile = profileService.findById(id).orElseThrow(AccountNotFoundException::new);
        contactService.addContact(myProfile).accept(profile);
        return "redirect:/profile/{id}";
    }
}
