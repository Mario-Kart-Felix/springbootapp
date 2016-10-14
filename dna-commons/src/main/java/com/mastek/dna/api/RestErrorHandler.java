package com.mastek.dna.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
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
		final ValidationErrorList validationErrors = new ValidationErrorList();

		final List<FieldError> fieldErrors = methodArgumentNotValidException.getBindingResult().getFieldErrors();
		fieldErrors.forEach((f) -> validationErrors.getErrors().add(new ValidationError(f.getField(), resolveLocalizedErrorMessage(f))));

		final ObjectError objectError = methodArgumentNotValidException.getBindingResult().getGlobalError();
		if (null != objectError)
		{
			validationErrors.getErrors().add(new ValidationError(objectError.getObjectName(), resolveLocalizedErrorMessage(objectError)));
		}

		return validationErrors;
	}

	private String resolveLocalizedErrorMessage(final MessageSourceResolvable messageSourceResolvable)
	{
		return messageSource.getMessage(messageSourceResolvable, LocaleContextHolder.getLocale());
	}
}