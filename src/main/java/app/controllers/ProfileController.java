package app.controllers;

import app.entities.*;
import app.exceptions.AccountNotFoundException;
import app.forms.EducationForm;
import app.services.AccountDetails;
import app.services.EducationService;
import app.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/profile")
public class ProfileController {
    private final ProfileService profileService;
    private final EducationService educationService;

    @Autowired
    public ProfileController(ProfileService profileService, EducationService educationService) {
        this.profileService = profileService;
        this.educationService = educationService;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public String getProfileByIdPage(@PathVariable Long id,
                                     ModelMap modelMap,
                                     @AuthenticationPrincipal AccountDetails account) {
        final Profile profile = profileService.findById(id)
                .orElseThrow(AccountNotFoundException::new);
        final Account user = profileService.findAccountById(account.getUser().getId())
                .orElseThrow(AccountNotFoundException::new);
        modelMap.put("profile", profile);
        modelMap.put("user", user);
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

        profileService.add(workExperience).to(profile);
        return "redirect:/profile";
    }
}
