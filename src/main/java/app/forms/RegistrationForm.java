package app.forms;

import lombok.Data;

@Data
public class RegistrationForm {
    protected String email;
    protected String password;
    protected String phoneNumber;
    protected String location;
}
