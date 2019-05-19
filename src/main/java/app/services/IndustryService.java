package app.services;

import app.entities.Industry;
import app.repositories.IndustryRepository;
import app.repositories.JobsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IndustryService {
    private final IndustryRepository industryRepository;

    @Autowired
    public IndustryService(JobsRepository jobsRepository, IndustryRepository industryRepository) {
        this.industryRepository = industryRepository;
    }

    public List<Industry> getAllIndustries() {
        return industryRepository.findAll();
    }

    public Optional<Industry> industryById(Long id) {
        return industryRepository.findById(id);
    }

    public Optional<Industry> industryByName(String name) {
        return industryRepository.findByName(name);
    }
}
