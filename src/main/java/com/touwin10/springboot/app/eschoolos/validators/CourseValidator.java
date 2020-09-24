package com.touwin10.springboot.app.eschoolos.validators;

import com.touwin10.springboot.app.eschoolos.model.Course;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

class CourseValidator implements Validator {

    CourseValidator(Object target, Errors errors) {
        validate(target, errors);
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Course.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "courseCode",
                "error.courseCode",
                "Course Code is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name",
                "error.name",
                "Name is required");
    }
}
