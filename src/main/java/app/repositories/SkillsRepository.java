package app.repositories;

import app.entities.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SkillsRepository extends JpaRepository<Skill, Long> {
    Optional<Skill> findByName(String name);

    default Skill create(String skillName) {
        return save(new Skill(skillName));
    }
}
