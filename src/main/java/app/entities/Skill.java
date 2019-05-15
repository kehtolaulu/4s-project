package app.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "skill")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    public Skill() {}

    public Skill(String skillName) {
        this.name = skillName;
    }
}
