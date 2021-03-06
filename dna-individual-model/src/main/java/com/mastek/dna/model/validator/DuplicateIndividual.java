package com.mastek.dna.model.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DuplicateIndividualValidator.class)
@Documented
public @interface DuplicateIndividual
{
	String message() default "{com.mastek.dna.model.validation.Individual.duplicate.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}