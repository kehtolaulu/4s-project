package app.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Data
@Entity
@Table(name = "profile_skills")
public class ProfileSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "skill_id")
    private Skill skill;

    @ManyToMany
    @JoinTable(
            name = "like_to_profile_skill",
            joinColumns = {@JoinColumn(name = "profile_skill_id")},
            inverseJoinColumns = {@JoinColumn(name = "account_id")}
    )
    private List<Account> likedBy = new LinkedList<>();

    public ProfileSkill() {}

    public ProfileSkill(Profile profile, Skill skill) {
        this.profile = profile;
        this.skill = skill;
    }

    public String getName() {
        return skill.getName();
    }
}
