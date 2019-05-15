package app.services;

import app.entities.*;
import app.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileService {
    private final AccountRepository accountRepository;
    private final ProfileRepository profileRepository;
    private final SkillsRepository skillsRepository;
    private final ProfileSkillRepository profileSkillRepository;
    private final WorkExperienceRepository workExperienceRepository;

    @Autowired
    public ProfileService(AccountRepository accountRepository,
                          ProfileRepository profileRepository,
                          SkillsRepository skillsRepository,
                          ProfileSkillRepository profileSkillRepository,
                          WorkExperienceRepository workExperienceRepository) {
        this.accountRepository = accountRepository;
        this.profileRepository = profileRepository;
        this.skillsRepository = skillsRepository;
        this.profileSkillRepository = profileSkillRepository;
        this.workExperienceRepository = workExperienceRepository;
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
}
