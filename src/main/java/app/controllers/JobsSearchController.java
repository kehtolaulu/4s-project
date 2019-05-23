package app.controllers;

import app.entities.Industry;
import app.entities.Job;
import app.services.AccountDetails;
import app.services.IndustryService;
import app.services.JobsService;
import app.services.ProfileService;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/search")
public class JobsSearchController {
    private final JobsService jobsService;
    private final ProfileService profileService;
    private final IndustryService industryService;

    public JobsSearchController(JobsService jobsService, ProfileService profileService, IndustryService industryService) {
        this.jobsService = jobsService;
        this.profileService = profileService;
        this.industryService = industryService;
    }

    @ResponseBody
    @GetMapping(path = "/jobs", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Map> searchJobs(@RequestParam(value = "position", required = false) String position,
                                @RequestParam(value = "industry", required = false) Long industryId,
                                @RequestParam(value = "employment", required = false) String employment,
                                @AuthenticationPrincipal AccountDetails user) {
        final Industry industry = industryId == null ? null : industryService.industryById(industryId).orElse(null);
        final List<Job> jobs = jobsService.searchJobs(position, industry, employment);
        return jobs.stream().map(this::jsonify).collect(Collectors.toList());
    }

    private Map jsonify(Job job) {
        return new TreeMap<String, Object>() {{
            put("id", job.getId());
            put("position", job.getPosition());
            put("company", job.getCompany().getName());
            put("jobFunctions", job.getJobFunctions());
            put("publishedAt", job.getPublishedAt().toString());
        }};
    }

    @GetMapping("/jobs.html")
    public String getSearchPage(ModelMap modelMap) {
        final List<Industry> industries = industryService.getAllIndustries();
        modelMap.put("industries", industries);
        return "search_jobs";
    }

}
