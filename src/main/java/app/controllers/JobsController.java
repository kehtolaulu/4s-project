package app.controllers;

import app.entities.Application;
import app.entities.Job;
import app.entities.Profile;
import app.exceptions.AccountNotFoundException;
import app.exceptions.JobNotFoundException;
import app.services.AccountDetails;
import app.services.ApplicationsService;
import app.services.JobsService;
import app.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping(path = "/jobs")
public class JobsController {
    private final JobsService jobsService;
    private final ProfileService profileService;
    private final ApplicationsService applicationsService;

    @Autowired
    public JobsController(JobsService jobsService, ProfileService profileService, ApplicationsService applicationsService) {
        this.jobsService = jobsService;
        this.profileService = profileService;
        this.applicationsService = applicationsService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getJobsPage(ModelMap modelMap) {
        final List<Job> jobs = jobsService.getAllJobs();
        modelMap.put("jobs", jobs);
        return "jobs";
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public String getJobPage(@PathVariable Long id, ModelMap modelMap) {
        final Job job = jobsService.jobById(id).orElseThrow(AccountNotFoundException::new);
        final Profile profile = profileService.findById(id).orElseThrow(AccountNotFoundException::new);
        final List<Job> applied = profile.getApplications();
        modelMap.put("job", job);
        modelMap.put("applied", applied);
        return "job";
    }

    @RequestMapping(path = "/{id}/apply", method = RequestMethod.POST)
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

    @RequestMapping(path = "{id}/save", method = RequestMethod.POST)
    public String save(@PathVariable Long id, @AuthenticationPrincipal AccountDetails account) {
        final Job job = jobsService.jobById(id).orElseThrow(JobNotFoundException::new);
        final Profile profile = profileService.findById(account.getUser().getId()).orElseThrow(AccountNotFoundException::new);

    }
}
