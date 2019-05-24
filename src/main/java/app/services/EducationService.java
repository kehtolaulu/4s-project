package app.services;

import app.entities.Education;
import app.entities.Profile;
import app.forms.EducationForm;
import app.repositories.EducationRepository;
import app.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EducationService {
    private final EducationRepository educationRepository;
    private final ProfileRepository profileRepository;

    @Autowired
    public EducationService(EducationRepository educationRepository, ProfileRepository profileRepository) {
        this.educationRepository = educationRepository;
        this.profileRepository = profileRepository;
    }

    public Education save(EducationForm educationForm, Profile profile) {
        Education education = new Education();
        int yearStarted = Integer.parseInt(educationForm.getStartedAt());
        education.setStartedAt(yearStarted);

        int yearFinished = Integer.parseInt(educationForm.getFinishedAt());
        education.setFinishedAt(yearFinished);

        education.setDegree(educationForm.getDegree());
        education.setField(educationForm.getField());
        education.setSchool(educationForm.getSchool());
        education.setProfile(profile);
        educationRepository.save(education);

        profile.getEducations().add(education);
        profileRepository.save(profile);
        return education;
    }
}
