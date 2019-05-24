package app.services;

import app.entities.Account;
import app.entities.ProfileSkill;
import app.entities.Skill;
import app.exceptions.SkillNotFoundException;
import app.repositories.ProfileSkillRepository;
import app.repositories.SkillsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
public class SkillService {
    private final SkillsRepository skillsRepository;
    private final ProfileSkillRepository profileSkillRepository;

    @Autowired
    public SkillService(SkillsRepository skillsRepository, ProfileSkillRepository profileSkillRepository) {
        this.skillsRepository = skillsRepository;
        this.profileSkillRepository = profileSkillRepository;
    }

    public Skill getSkillByName(String name) {
        return skillsRepository.findByNameIgnoreCase(name).orElse(null);
    }

    public Consumer<Account> likeProfileSkill(long skillId) {
        final ProfileSkill skill = profileSkillRepository.findById(skillId).orElseThrow(SkillNotFoundException::new);
        return account -> {
            skill.getLikedBy().add(account);
            profileSkillRepository.save(skill);
        };
    }
}
