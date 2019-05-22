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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(path = "/jobs")
public class JobsController {
    private final JobsService jobsService;
    private final ProfileService profileService;
    private final ApplicationsService applicationsService;
    private final CompanyService companyService;
    private final IndustryService industryService;

    @Autowired
    public JobsController(JobsService jobsService, ProfileService profileService, ApplicationsService applicationsService, CompanyService companyService, IndustryService industryService) {
        this.jobsService = jobsService;
        this.profileService = profileService;
        this.applicationsService = applicationsService;
        this.companyService = companyService;
        this.industryService = industryService;
    }

    @GetMapping
    public String getJobsPage(ModelMap modelMap, @AuthenticationPrincipal AccountDetails account) {
        final List<Job> jobs = jobsService.getAllJobs();
        if (account.getUser().getType().equals("HUMAN")) {
            final Profile profile = profileService.findById(account.getUser().getId()).orElseThrow(AccountNotFoundException::new);
            final List<Job> saved = profile.getSavedJobs();
            final List<Application> applied = profile.getApplications();
            modelMap.put("saved", saved);
            modelMap.put("applied", applied);
        } else {
            final Company company = companyService.companyById(account.getUser().getId()).orElseThrow(AccountNotFoundException::new);
            final List<Job> companyJobs = company.getJobs();
            modelMap.put(("ours"), companyJobs);
        }
        modelMap.put("jobs", jobs);
        return "jobs";
    }

}
