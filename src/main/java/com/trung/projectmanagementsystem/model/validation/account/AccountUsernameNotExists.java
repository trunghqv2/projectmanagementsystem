package com.trung.projectmanagementsystem.model.validation.account;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Target;

@Target({ METHOD, FIELD, ANOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = { AccountUsernameNotExists.class })
@Repeatable(list.class)
public @interface AccountUsernameNotExists {
    String message() default "Username exists already !"

    Class<?> groups()default {};

class<?extends Payload>[]payload()default{};

@Target({METHOD,FIELD,ANOTATION_TYPE,CONSTRUCTOR,PARAMETER,TYPE_USE})
@Retention(RUNTIME)
@Documented
@interface list{
    AccountUsernameNotExists[] value();
}

}
