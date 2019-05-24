package app.entities.validation;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class PhoneNumberValidation implements ConstraintValidator<PhoneNumberConstraint, String> {
    public boolean isValid(String obj, ConstraintValidatorContext context) {
//        return obj.matches("(\\+?\\d\\s?)?\\(?([0-9]{3})\\)?([ .-]?)([0-9]{3})\\2([0-9]{4})");
        return obj.matches("[0-9]{11}");
    }
}
