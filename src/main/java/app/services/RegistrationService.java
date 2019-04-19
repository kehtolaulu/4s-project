package app.services;

import app.forms.CompanyForm;
import app.forms.ProfileForm;

public interface RegistrationService {
    void register(CompanyForm form);
    void register(ProfileForm form);
}
