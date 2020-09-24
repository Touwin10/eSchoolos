package com.touwin10.springboot.app.eschoolos.validators;

import com.touwin10.springboot.app.eschoolos.model.Professor;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

class ProfessorValidator implements Validator {

    ProfessorValidator(Object target, Errors errors) {
        validate(target, errors);
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Professor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "professorNumber",
                "error.professorNumber",
                "Professor Number is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName",
                "error.firstName",
                "First name is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName",
                "error.lastName",
                "Last name is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthDate",
                "error.birthDate",
                "Date of birth is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "joiningDate",
                "error.joiningDate",
                "Joining Date is required");
    }
}
