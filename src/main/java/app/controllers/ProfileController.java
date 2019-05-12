package app.controllers;

import app.entities.*;
import app.forms.EducationForm;
import app.repositories.*;
import app.services.AccountDetails;
import app.services.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/profile")
public class ProfileController {
    private final ProfileRepository profileRepository;
    private final SkillsRepository skillsRepository;
    private final WorkExperienceRepository workExperienceRepository;
    private final ProfileSkillRepository profileSkillRepository;
    private final EducationService educationService;

    @Autowired
    public ProfileController(ProfileRepository profileRepository, SkillsRepository skillsRepository, EducationRepository educationRepository, WorkExperienceRepository workExperienceRepository, ProfileSkillRepository profileSkillRepository, EducationService educationService) {
        this.profileRepository = profileRepository;
        this.skillsRepository = skillsRepository;
        this.workExperienceRepository = workExperienceRepository;
        this.profileSkillRepository = profileSkillRepository;
        this.educationService = educationService;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public String getProfileByIdPage(@PathVariable Long id,
                                     ModelMap modelMap,
                                     @AuthenticationPrincipal AccountDetails account) {
        final Profile profile = profileRepository.findById(id).get();
        final Account user = profileRepository.findById(account.getUser().getId()).get();
        modelMap.put("profile", profile);
        modelMap.put("user", user);
        return "profile";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getProfilePage(@AuthenticationPrincipal AccountDetails account, ModelMap modelMap) {
        final Account user = account.getUser();
        final Profile profile = profileRepository.findById(user.getId()).get();
        modelMap.put("profile", profile);
        modelMap.put("user", profile);
        return "profile";
    }

    @RequestMapping(path = "/{id}/edit", method = RequestMethod.POST)
    public String editStatus(@AuthenticationPrincipal AccountDetails account, String status, String location) {
        final Profile profile = profileRepository.findById(account.getUser().getId()).get();
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
        profileRepository.save(profile);
        return "redirect:/profile";
    }

    @PostMapping(path = "/{id}/add_skill")
    public String addSkill(@AuthenticationPrincipal AccountDetails account, String skillName) {
        Skill skill = new Skill();
        skill.setName(skillName);
        skillsRepository.save(skill);

        final Profile profile = profileRepository.findById(account.getUser().getId()).get();

        ProfileSkill profileSkill = new ProfileSkill();
        profileSkill.setProfile(profile);
        profileSkill.setSkill(skill);
        profileSkillRepository.save(profileSkill);

        profile.getSkills().add(profileSkill);
        profileRepository.save(profile);
        return "redirect:/profile";
    }

    @PostMapping(path = "/{id}/add_edu")
    public String addEducation(@AuthenticationPrincipal AccountDetails account, EducationForm educationForm) {
        final Profile profile = profileRepository.findById(account.getUser().getId()).get();
        final Education education = educationService.save(educationForm, profile);
        profile.getEducations().add(education);
        profileRepository.save(profile);
        return "redirect:/profile";
    }

    @PostMapping(path = "/{id}/add_work")
    public String addWorkExperience(@AuthenticationPrincipal AccountDetails account, WorkExperience workExperience) {
        final Profile profile = profileRepository.findById(account.getUser().getId()).get();
        profile.getWorkExperiences().add(workExperience);
        workExperienceRepository.save(workExperience);
        profileRepository.save(profile);
        return "redirect:/profile";
    }

}
