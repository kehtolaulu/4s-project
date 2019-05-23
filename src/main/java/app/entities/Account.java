package app.entities;

import app.entities.validation.EmailConstraint;
import app.entities.validation.PhoneNumberConstraint;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "account")
public abstract class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @EmailConstraint
    @Column(name = "email")
    protected String email;

    @Column(name = "password_hash")
    protected String passwordHash;

    @Column(name = "location")
    protected String location;

    @PhoneNumberConstraint
    @Column(name = "phone_number")
    protected String phoneNumber;

    @OneToMany
    @JoinColumn(name = "author_id")
    protected List<Post> posts;

    abstract public String getName();

    abstract public String getType();
}
