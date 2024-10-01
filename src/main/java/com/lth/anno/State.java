package com.lth.anno;


import com.lth.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;


@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
// 指定校验规则的类StateValidation
@Constraint(validatedBy = {StateValidation.class})
public @interface State {

    // 提供校验规则的信息
    String message() default "State的值只能是已发布或草稿";

    // 指定分组
    Class<?>[] groups() default {};

    // 负载
    Class<? extends Payload>[] payload() default {};

}


