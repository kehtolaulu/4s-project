package app.controllers;

import app.entities.Profile;
import app.services.AccountDetails;
import app.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "/people")
public class PeopleController {

    private final ProfileService profileService;

    @Autowired
    public PeopleController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    public String getPeoplePage() {
        return "people";
    }

    @ResponseBody
    @GetMapping(path = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Map> searchJobs(@RequestParam(value = "name", required = false) String name,
                                @AuthenticationPrincipal AccountDetails user) {
        final String[] split = (name + " ").split(" ");
        String firstName = split[0];
        String lastName;
        if (split.length < 2) {
            lastName = "";
        } else {
            lastName = split[1];
        }
        final List<Profile> people = profileService.findByName(firstName, lastName);
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
