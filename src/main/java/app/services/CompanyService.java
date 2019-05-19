package app.services;

import app.entities.Company;
import app.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyService {
    private CompanyRepository companyRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Optional<Company> companyById(Long id) {
        return companyRepository.findById(id);
    }

    public void save(Company company) {
        companyRepository.save(company);
    }

}
