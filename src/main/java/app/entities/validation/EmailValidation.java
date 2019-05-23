package app.entities.validation;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.function.Predicate;

@Component
public class EmailValidation implements ConstraintValidator<EmailConstraint, String> {
    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        String[] split = email.toLowerCase().split("@");
        if (split.length != 2) {
            return false;
        }
        String username = split[0];
        String domain = split[1];

        return validUsername(username) && validDomain(domain);
    }

    private boolean validUsername(String username) {
        Predicate<Character> isAllowedSymbol = c -> 'a' <= c && c <= 'z' || '0' <= c && c <= '9' || c == '.';
        boolean dotBefore = true;

        for (char c : username.toCharArray()) {
            if (c == '.' && dotBefore) {
                return false;
            }

            if (!isAllowedSymbol.test(c)) {
                return false;
            }

            dotBefore = c == '.';
        }

        return !dotBefore;
    }

    private boolean validDomain(String domain) {
        if (domain.isEmpty() || domain.length() > 253) {
            return false;
        }
        String[] labels = domain.split("\\.");
        return Arrays.stream(labels).allMatch(this::validLabel);
    }

    private boolean validLabel(String label) {
        if (label.isEmpty() || label.length() > 63 || label.startsWith("-") || label.endsWith("-")) {
            return false;
        }

        Predicate<Character> isAllowedSymbol = c -> 'a' <= c && c <= 'z' || '0' <= c && c <= '9' || c == '-';

        return label.codePoints().mapToObj(i -> (char) i).allMatch(isAllowedSymbol);
    }
}
