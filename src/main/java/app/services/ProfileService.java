package app.services;

import app.entities.*;
import app.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {
    private final AccountRepository accountRepository;
    private final ProfileRepository profileRepository;
    private final SkillsRepository skillsRepository;
    private final ProfileSkillRepository profileSkillRepository;
    private final WorkExperienceRepository workExperienceRepository;
    private final ApplicationsRepository applicationsRepository;

    @Autowired
    public ProfileService(AccountRepository accountRepository,
                          ProfileRepository profileRepository,
                          SkillsRepository skillsRepository,
                          ProfileSkillRepository profileSkillRepository,
                          WorkExperienceRepository workExperienceRepository,
                          ApplicationsRepository applicationsRepository) {
        this.accountRepository = accountRepository;
        this.profileRepository = profileRepository;
        this.skillsRepository = skillsRepository;
        this.profileSkillRepository = profileSkillRepository;
        this.workExperienceRepository = workExperienceRepository;
        this.applicationsRepository = applicationsRepository;
    }

    public List<Profile> findByName(String firstName, String lastName) {
        final ExampleMatcher exampleMatcher = ExampleMatcher.matchingAll()
                .withMatcher("firstName", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("lastName", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withIgnoreCase();
        final Profile profile = new Profile();
        if (!firstName.equals("")) {
            profile.setFirstName(firstName);
        }
        if (!lastName.equals("")) {
            profile.setLastName(lastName);
        }
        final Example<Profile> filter = Example.of(profile, exampleMatcher);

        final List<Profile> all = profileRepository.findAll(filter);

        final Profile profile2 = new Profile();
        if (!lastName.equals("")) {
            profile2.setFirstName(lastName);
        }
        if (!firstName.equals("")) {
            profile2.setLastName(firstName);
        }

        final Example<Profile> filter2 = Example.of(profile2, exampleMatcher);

        final List<Profile> all2 = profileRepository.findAll(filter2);

        all.addAll(all2);
        return all;
    }

    public List<Profile> getPeopleBySkills(List<Long> skills) {
        return profileRepository.getPeopleBySkills(skills);
    }

    public Optional<Profile> findById(Long id) {
        return profileRepository.findById(id);
    }

    public Optional<Account> findAccountById(Long id) {
        return accountRepository.findById(id);
    }

    public void save(Profile profile) {
        profileRepository.save(profile);
    }

    public Skill createSkill(String skillName) {
        Optional<Skill> skill = skillsRepository.findByName(skillName);
        return skill.orElseGet(() -> skillsRepository.create(skillName));
    }

    public BindToProfile add(Skill skill) {
        return profile -> {
            ProfileSkill profileSkill = new ProfileSkill(profile, skill);
            profileSkillRepository.save(profileSkill);
            profile.getSkills().add(profileSkill);
            profileRepository.save(profile);
        };
    }

    public BindToProfile add(WorkExperience workExperience) {
        return profile -> {
            profile.getWorkExperiences().add(workExperience);
            workExperienceRepository.save(workExperience);
            profileRepository.save(profile);
        };
    }

    public BindToProfile add(Company company) {
        return profile -> {
            profile.getInterests().add(company);
            profileRepository.save(profile);
        };
    }

    public BindToProfile delete(Company company) {
        return profile -> {
            profile.getInterests().remove(company);
            profileRepository.save(profile);
        };
    }

    public BindToProfile saveJob(Job job) {
        return profile -> {
            profile.getSavedJobs().add(job);
            profileRepository.save(profile);
        };
    }

    public BindToProfile saveApplication(Application application) {
        return profile -> {
            profile.getApplications().add(application);
            applicationsRepository.save(application);
            profileRepository.save(profile);
        };
    }

}
