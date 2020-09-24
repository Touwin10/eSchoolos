package com.touwin10.springboot.app.eschoolos.validators;

import org.springframework.validation.Errors;

public class ValidatorsUtils {
    public static void professorValidator(Object target, Errors errors) {
        new ProfessorValidator(target, errors);
    }

    public static void studentValidator(Object target, Errors errors) {
        new StudentValidator(target, errors);
    }

    public static void courseValidator(Object target, Errors errors) {
        new CourseValidator(target, errors);
    }
}
