package app.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "application")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    private String number;

    private String email;

    @Column(name = "is_accepted")
    private Integer isAccepted;

    @Column(name = "is_rejected")
    private Integer isRejected;

    @Column(name = "publishedAt")
    private LocalDateTime publishedAt;

}
