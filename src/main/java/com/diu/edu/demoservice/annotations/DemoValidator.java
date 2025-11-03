package com.diu.edu.demoservice.annotations;



import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DemoValidator implements ConstraintValidator<DemoValidation, String> {

    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        log.info("Step 1 Item info DAO");
        log.info(name);
        return name.equals("hello");
    }
}
