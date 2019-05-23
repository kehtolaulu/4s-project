package app.services;

import app.entities.Skill;
import app.repositories.SkillsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkillService {
    private final SkillsRepository skillsRepository;

    @Autowired
    public SkillService(SkillsRepository skillsRepository) {
        this.skillsRepository = skillsRepository;
    }

    public Skill getSkillByName(String name) {
        return skillsRepository.findByNameIgnoreCase(name).orElse(null);
    }
}
