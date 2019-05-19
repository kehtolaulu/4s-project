package app.controllers;

import app.entities.Company;
import app.entities.Industry;
import app.entities.Job;
import app.exceptions.AccountNotFoundException;
import app.exceptions.IndustryNotFoundException;
import app.services.AccountDetails;
import app.services.CompanyService;
import app.services.IndustryService;
import app.services.JobsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping(path = "/jobs/create")
public class CreateJobController {
    private final JobsService jobsService;
    private final CompanyService companyService;
    private final IndustryService industryService;

    @Autowired
    public CreateJobController(JobsService jobsService,
                               CompanyService companyService,
                               IndustryService industryService) {
        this.jobsService = jobsService;
        this.companyService = companyService;
        this.industryService = industryService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getJobCreatePage(ModelMap modelMap) {
        final List<Industry> industries = industryService.getAllIndustries();
        modelMap.put("industries", industries);
        return "create_job";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createJob(String employmentType,
                            @AuthenticationPrincipal AccountDetails account,
                            String position, Long industry, String seniorityLevel, String jobFunctions) {
        final Company company = companyService.companyById(account.getUser().getId())
                .orElseThrow(AccountNotFoundException::new);
        Job job = new Job();
        String newEmploymentType = "";
        if (employmentType != null) {
            switch (Integer.parseInt(employmentType)) {
                case 1:
                    newEmploymentType = "";
                    break;
                case 2:
                    newEmploymentType = "Half-time";
                    break;
                case 3:
                    newEmploymentType = "Full-time";
                    break;
                case 4:
                    newEmploymentType = "Remote";
                    break;
            }
        }
        job.setEmploymentType(newEmploymentType);
        final Industry industryById = industryService.industryById(industry).orElseThrow(IndustryNotFoundException::new);
        job.setIndustry(industryById);
        job.setJobFunctions(jobFunctions);
        job.setSeniorityLevel(seniorityLevel);
        job.setPosition(position);
        jobsService.create(job, company);
        return "redirect:/jobs";
    }
}
