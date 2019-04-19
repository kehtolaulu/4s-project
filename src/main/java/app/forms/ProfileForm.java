package app.forms;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProfileForm extends RegistrationForm {
    private String firstName;
    private String lastName;
    private String birthDate;
}
