package app.controllers;

import app.entities.Profile;
import app.entities.Skill;
import app.services.AccountDetails;
import app.services.ProfileService;
import app.services.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/by_skill")
public class PeopleSearchController {
    private final ProfileService profileService;
    private final SkillService skillService;

    @Autowired
    public PeopleSearchController(ProfileService profileService, SkillService skillService) {
        this.profileService = profileService;
        this.skillService = skillService;
    }

    @GetMapping
    public String getPeoplePage() {
        return "search_people";
    }

    @ResponseBody
    @GetMapping(path = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Map> searchJobs(@RequestParam(value = "skills") String query,
                                @AuthenticationPrincipal AccountDetails user) {
        List<String> skillNames = Arrays.asList(query.split(" "));
        final List<Long> skills = skillNames.stream().map(skillService::getSkillByName).map(Skill::getId).collect(Collectors.toList());
        final List<Profile> people = profileService.getPeopleBySkills(skills);
        return people.stream().map(this::jsonify).collect(Collectors.toList());
    }
    private Map jsonify(Profile profile) {
        return new TreeMap<String, Object>() {{
            put("id", profile.getId());
            put("firstName", profile.getFirstName());
            put("lastName", profile.getLastName());
            put("location", profile.getLocation());
        }};
    }

}
