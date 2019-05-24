package app.controllers;

import app.services.AccountDetails;
import app.services.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/profile")
public class LikeController {
    private final SkillService skillService;

    @Autowired
    public LikeController(SkillService skillService) {
        this.skillService = skillService;
    }

    @PostMapping("/{id}/skills/{skillId}/like")
    public String like(@AuthenticationPrincipal AccountDetails accountDetails, @PathVariable long id, @PathVariable long skillId) {
        skillService.likeProfileSkill(skillId).accept(accountDetails.getUser());
        return "redirect:/profile/" + id;
    }
}
