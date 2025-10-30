package com.diu.edu.demoservice.annotations;



import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DemoValidator implements ConstraintValidator<DemoValidation, String> {


    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println("Step 1 Item info DAO");
        System.out.println(name);
        return name.equals("hello");
    }
}
