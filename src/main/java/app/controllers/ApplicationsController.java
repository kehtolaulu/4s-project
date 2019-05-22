package app.controllers;

import app.entities.Application;
import app.entities.Company;
import app.entities.Job;
import app.exceptions.AccountNotFoundException;
import app.exceptions.ApplicationNotFoundException;
import app.services.AccountDetails;
import app.services.ApplicationsService;
import app.services.CompanyService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "/applications")
public class ApplicationsController {
    private final ApplicationsService applicationsService;
    private final CompanyService companyService;

    public ApplicationsController(ApplicationsService applicationsService, CompanyService companyService) {
        this.applicationsService = applicationsService;
        this.companyService = companyService;
    }

    @GetMapping
    public String getAppsPage(ModelMap modelMap, @AuthenticationPrincipal AccountDetails account) {
        final Company company = companyService.companyById(account.getUser().getId()).orElseThrow(AccountNotFoundException::new);
        final List<Job> companyJobs = company.getJobs();
        final List<Application> applications = companyJobs.stream().flatMap(j -> j.getApplications().stream()).distinct().collect(Collectors.toList());
        modelMap.put("applications", applications);
        return "applications";
    }

    @PostMapping(path = "/{id}/accept")
    public String acceptApplication(@PathVariable Long id) {
        final Application application = applicationsService.applicationById(id).orElseThrow(ApplicationNotFoundException::new);
        applicationsService.acceptApplication(application);
        return "redirect:/applications";
    }

    @PostMapping(path = "/{id}/reject")
    public String rejectApplication(@PathVariable Long id) {
        final Application application = applicationsService.applicationById(id).orElseThrow(ApplicationNotFoundException::new);
        applicationsService.rejectApplication(application);
        return "redirect:/applications";
    }
}
