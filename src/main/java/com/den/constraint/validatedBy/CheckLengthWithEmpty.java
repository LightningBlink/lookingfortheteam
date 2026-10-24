package com.den.constraint.validatedBy;

import com.den.constraint.LengthWithEmpty;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Denis on 15.05.2015.
 */
public class CheckLengthWithEmpty implements ConstraintValidator<LengthWithEmpty, String> {

    private Integer max;
    private Integer min;


    @Override
    public void initialize(LengthWithEmpty lengthWithEmpty) {
        this.min = lengthWithEmpty.min();
        this.max = lengthWithEmpty.max();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

        if(s == null || s.length() == 0){
            return true;
        }

        int length = s.length();

        return !((max != null && length > max) || (min != null && length < min));

    }
}
