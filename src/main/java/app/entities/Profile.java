package app.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Data
@ToString(exclude = {"skills", "interests", "savedJobs", "applications", "workExperiences", "educations"})
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

    @Column(name = "status")
    private String status;

    @OneToMany
    @JoinColumn(name = "profile_id")
    List<ProfileSkill> skills = new LinkedList<>();

    @OneToMany
    @JoinColumn(name = "profile_id")
    List<Education> educations = new LinkedList<>();

    @OneToMany
    @JoinColumn(name = "profile_id")
    List<WorkExperience> workExperiences = new LinkedList<>();

    @ManyToMany
    @JoinTable(
            name = "interest",
            joinColumns = {@JoinColumn(name = "profile_id")},
            inverseJoinColumns = {@JoinColumn(name = "company_id")})
    List<Account> interests = new LinkedList<>();

    @ManyToMany
    @JoinTable(
            name = "saved_job",
            joinColumns = {@JoinColumn(name = "profile_id")},
            inverseJoinColumns = {@JoinColumn(name = "job_id")}
    )
    List<Job> savedJobs = new LinkedList<>();

    @ManyToMany
    @JoinTable(
            name = "application",
            joinColumns = {@JoinColumn(name = "profile_id")},
            inverseJoinColumns = {@JoinColumn(name = "job_id")}
    )
    List<Job> applications = new LinkedList<>();

    @Override
    public String getName() {
        return firstName + " " + lastName;
    }

    @Override
    public String getType() {
        return "HUMAN";
    }
}
