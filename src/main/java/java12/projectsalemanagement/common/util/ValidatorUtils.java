package java12.projectsalemanagement.common.util;

import javax.validation.ConstraintValidatorContext;

public class ValidatorUtils {
    public static void addError(ConstraintValidatorContext context,
                                String message) {
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
    }
}
