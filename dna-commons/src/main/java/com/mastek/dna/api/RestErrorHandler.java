package com.mastek.dna.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RestErrorHandler
{
	private final MessageSource messageSource;

	@Autowired
	public RestErrorHandler(final MessageSource messageSource)
	{
		this.messageSource = messageSource;
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ValidationErrorList processValidationError(final MethodArgumentNotValidException methodArgumentNotValidException)
	{
		final List<FieldError> fieldErrors = methodArgumentNotValidException.getBindingResult().getFieldErrors();

		final ValidationErrorList validationErrors = new ValidationErrorList();

		fieldErrors.forEach((f) -> validationErrors.getErrors().add(new ValidationError(f.getField(), resolveLocalizedErrorMessage(f))));

		return validationErrors;
	}

	private String resolveLocalizedErrorMessage(final FieldError fieldError)
	{
		return messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
	}
}