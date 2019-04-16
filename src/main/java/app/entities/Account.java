package app.entities;

import com.sun.istack.Nullable;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

//CREATE TABLE account (
//        id            SERIAL PRIMARY KEY,
//        email         TEXT, -- email to login
//        password_hash TEXT,
//        location      TEXT,
//        phone_number  TEXT
//        );
@Data
@ToString(exclude = {"skills", "interests", "savedJobs", "applications"})
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password_hash")
    private String passwordHash;

    @Column(name = "type")
    private String type;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "about")
    private String about;

    @Column(name = "place")
    private String place;

    @Column(name = "last_name")
    private String lastName;

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
}
