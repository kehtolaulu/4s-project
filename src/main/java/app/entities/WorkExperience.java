package app.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "work_experience")
public class WorkExperience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @Column(name = "position")
    private String position;

    @Column(name = "company")
    private String company;

    @Column(name = "started_at")
    private Integer startedAt;

    @Column(name = "finished_at")
    private Integer finishedAt;
}
