package app.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "company")
public class Company extends Account {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "company_id")
//    private Long companyId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

}
