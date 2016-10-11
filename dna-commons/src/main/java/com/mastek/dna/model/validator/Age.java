package com.mastek.dna.model.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AgeValidator.class)
@Documented
public @interface Age
{
	String message() default "com.mastek.dna.model.validation.Age.message";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	int min() default -1;

	int max() default -1;
}