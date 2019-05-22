package app.services;

import app.entities.Company;
import app.entities.Industry;
import app.entities.Job;
import app.repositories.JobsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JobsService {
    private final JobsRepository jobsRepository;

    @Autowired
    public JobsService(JobsRepository jobsRepository) {
        this.jobsRepository = jobsRepository;
    }

    public List<Job> getAllJobs() {
        return jobsRepository.findAllOrderByPublishedAtDesc();
    }

    public void create(Job job , Company company) {
        job.setCompany(company);
        job.setPublishedAt(LocalDateTime.now());
        jobsRepository.save(job);
    }

    public Optional<Job> jobById(Long id) {
        return jobsRepository.findById(id);
    }

    public List<Job> searchJobs(String position, Industry industry, String employmentType) {

        final ExampleMatcher exampleMatcher = ExampleMatcher.matchingAll()
                .withMatcher("position", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withIgnoreCase();
        final Job job = new Job();
        if (position != null && !position.isEmpty()) {
            job.setPosition(position);
        }
        if (industry != null) {
            job.setIndustry(industry);
        }
        if (employmentType != null && !employmentType.isEmpty()) {
            job.setEmploymentType(employmentType);
        }
        final Example<Job> filter = Example.of(job, exampleMatcher);

        final List<Job> all = jobsRepository.findAll(filter);
        return all;
    }
}
