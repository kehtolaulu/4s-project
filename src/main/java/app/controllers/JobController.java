package app.controllers;

import app.entities.Application;
import app.entities.Job;
import app.entities.Profile;
import app.exceptions.AccountNotFoundException;
import app.exceptions.JobNotFoundException;
import app.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "/jobs/{id}")
public class JobController {
    private final JobsService jobsService;
    private final ProfileService profileService;
    private final ApplicationsService applicationsService;
    private final CompanyService companyService;

    @Autowired
    public JobController(JobsService jobsService, ProfileService profileService, ApplicationsService applicationsService, CompanyService companyService) {
        this.jobsService = jobsService;
        this.profileService = profileService;
        this.applicationsService = applicationsService;
        this.companyService = companyService;
    }

    @GetMapping
    public String getJobPage(@PathVariable Long id, ModelMap modelMap, @AuthenticationPrincipal AccountDetails account) {
        final Job job = jobsService.jobById(id).orElseThrow(AccountNotFoundException::new);
        final Profile profile = profileService.findById(account.getUser().getId()).orElseThrow(AccountNotFoundException::new);
        final List<Job> applied = profile.getApplications().stream().map(Application::getJob).collect(Collectors.toList());
        final List<Job> saved = profile.getSavedJobs();
        modelMap.put("job", job);
        modelMap.put("applied", applied);
        modelMap.put("saved", saved);
        return "job";
    }

    @RequestMapping(path = "/apply", method = RequestMethod.POST)
    public String apply(@PathVariable Long id, @AuthenticationPrincipal AccountDetails account) {
        Application application = new Application();
        final Job job = jobsService.jobById(id).orElseThrow(JobNotFoundException::new);
        final Profile profile = profileService.findById(account.getUser().getId()).orElseThrow(AccountNotFoundException::new);
        application.setProfile(profile);
        application.setEmail(profile.getEmail());
        application.setNumber(profile.getPhoneNumber());
        application.setJob(job);
        applicationsService.save(application);
        return "redirect:/jobs";
    }

    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public String save(@PathVariable Long id, @AuthenticationPrincipal AccountDetails account) {
        final Job job = jobsService.jobById(id).orElseThrow(JobNotFoundException::new);
        final Profile profile = profileService.findById(account.getUser().getId()).orElseThrow(AccountNotFoundException::new);
        profileService.saveJob(job).to(profile);
        return "redirect:/jobs";
    }
}
