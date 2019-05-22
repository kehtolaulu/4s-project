package app.services;

import app.entities.Company;
import app.entities.Profile;
import app.forms.CompanyForm;
import app.forms.ProfileForm;
import app.repositories.CompanyRepository;
import app.repositories.ProfileRepository;
import app.repositories.neo4j.Neo4jProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    private final PasswordEncoder passwordEncoder;

    private final CompanyRepository companyRepository;
    private final ProfileRepository profileRepository;
    private final Neo4jProfileRepository neo4jProfileRepository;

    @Autowired
    public RegistrationServiceImpl(PasswordEncoder passwordEncoder,
                                   CompanyRepository companyRepository,
                                   ProfileRepository seekerRepository,
                                   Neo4jProfileRepository neo4jProfileRepository) {
        this.passwordEncoder = passwordEncoder;
        this.companyRepository = companyRepository;
        this.profileRepository = seekerRepository;
        this.neo4jProfileRepository = neo4jProfileRepository;
    }

    @Override
    public void register(CompanyForm form) {
        Company company = new Company();
        company.setName(form.getName());
        company.setLocation(form.getLocation());
        company.setEmail(form.getEmail());
        company.setPhoneNumber(form.getPhoneNumber());
        company.setPasswordHash(passwordEncoder.encode(form.getPassword()));
        companyRepository.save(company);
    }

    @Override
    public void register(ProfileForm form) {
        Profile profile = new Profile();
        profile.setFirstName(form.getFirstName());
        profile.setLastName(form.getLastName());
        profile.setLocation(form.getLocation());
        profile.setBirthDate(LocalDate.parse(form.getBirthDate()));
        profile.setEmail(form.getEmail());
        profile.setPhoneNumber(form.getPhoneNumber());
        profile.setPasswordHash(passwordEncoder.encode(form.getPassword()));
        final Profile savedProfile = profileRepository.save(profile);

        neo4jProfileRepository.createProfile(savedProfile.getId());
    }
}
