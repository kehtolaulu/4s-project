package app.entities;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "job")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @Column(name = "published_at")
    private LocalDateTime publishedAt;

    @Column(name = "content")
    private String content;

    @Column(name = "seniority_level")
    private String seniorityLevel;

    @ManyToOne
    @JoinColumn(name = "industry_id")
    private Industry industry;

    @Column(name = "employment_type")
    private String employmentType;

    @Column(name = "job_functions")
    private String jobFunctions;
}
