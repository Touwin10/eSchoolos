package com.touwin10.springboot.app.eschoolos.validators;

import com.touwin10.springboot.app.eschoolos.model.Student;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

class StudentValidator implements Validator {
    StudentValidator(Object target, Errors errors) {
        validate(target, errors);
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Student.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "studentNumber",
                "error.studentNumber",
                "Student Number is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName",
                "error.firstName",
                "First name is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName",
                "error.lastName",
                "Last name is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthDate",
                "error.birthDate",
                "Date of birth is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "enrollmentDate",
                "error.enrollmentDate",
                "Enrollment Date is required");
    }
}
