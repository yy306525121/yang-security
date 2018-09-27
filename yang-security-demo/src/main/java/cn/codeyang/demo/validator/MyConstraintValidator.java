package cn.codeyang.demo.validator;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object> {
    @Override
    public void initialize(MyConstraint constraintAnnotation) {
        System.out.println("init....");
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        System.out.println(value);
        return false;
    }
}
