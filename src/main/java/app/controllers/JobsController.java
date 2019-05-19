package app.controllers;

import app.entities.Application;
import app.entities.Company;
import app.entities.Job;
import app.entities.Profile;
import app.exceptions.AccountNotFoundException;
import app.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "/jobs")
public class JobsController {
    private final JobsService jobsService;
    private final ProfileService profileService;
    private final ApplicationsService applicationsService;
    private final CompanyService companyService;

    @Autowired
    public JobsController(JobsService jobsService, ProfileService profileService, ApplicationsService applicationsService, CompanyService companyService) {
        this.jobsService = jobsService;
        this.profileService = profileService;
        this.applicationsService = applicationsService;
        this.companyService = companyService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getJobsPage(ModelMap modelMap, @AuthenticationPrincipal AccountDetails account) {
        final List<Job> jobs = jobsService.getAllJobs();
        if (account.getUser().getType() == "HUMAN") {
            final Profile profile = profileService.findById(account.getUser().getId()).orElseThrow(AccountNotFoundException::new);
            final List<Job> saved = profile.getSavedJobs();
            final List<Application> applied = profile.getApplications();
            modelMap.put("saved", saved);
            modelMap.put("applied", applied);
        } else {
            final Company company = companyService.companyById(account.getUser().getId()).orElseThrow(AccountNotFoundException::new);
            final List<Job> companyJobs = company.getJobs();
            final List<Application> applications = companyJobs.stream().flatMap(j -> j.getApplications().stream()).distinct().collect(Collectors.toList());
            modelMap.put("applications", applications);
            modelMap.put(("ours"), companyJobs);
        }
        modelMap.put("jobs", jobs);
        return "jobs";
    }
}
