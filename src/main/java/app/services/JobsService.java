package app.services;

import app.entities.Company;
import app.entities.Job;
import app.repositories.JobsRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
}
