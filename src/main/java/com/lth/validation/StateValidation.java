package com.lth.validation;

import com.lth.anno.State;
import jakarta.validation.ConstraintValidator;

// <给哪个注解提供规则,校验的数据类型>
public class StateValidation implements ConstraintValidator<State, String> {

    @Override
    public boolean isValid(String value, jakarta.validation.ConstraintValidatorContext context) {
        return "已发布".equals(value) || "草稿".equals(value);
    }

}
