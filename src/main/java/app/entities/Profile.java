package app.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Data
@Entity
@Table(name = "profile")
public class Profile extends Account {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "profile_id")
//    private Long profileId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @ManyToMany
    @JoinTable(
            name = "profile_skills",
            joinColumns = {@JoinColumn(name = "profile_id")},
            inverseJoinColumns = {@JoinColumn(name = "skill_id")}
    )
    List<Skill> skills = new LinkedList<>();

    @ManyToMany
    @JoinTable(
            name = "interest",
            joinColumns = {@JoinColumn(name = "account_id")},
            inverseJoinColumns = {@JoinColumn(name = "company_id")})
    List<Account> interests = new LinkedList<>();

    @ManyToMany
    @JoinTable(
            name = "saved_job",
            joinColumns = {@JoinColumn(name = "account_id")},
            inverseJoinColumns = {@JoinColumn(name = "job_id")}
    )
    List<Job> savedJobs = new LinkedList<>();

    @ManyToMany
    @JoinTable(
            name = "application",
            joinColumns = {@JoinColumn(name = "account_id")},
            inverseJoinColumns = {@JoinColumn(name = "job_id")}
    )
    List<Job> applications = new LinkedList<>();

    @Override
    public String getName() {
        return firstName + " " + lastName;
    }
}
