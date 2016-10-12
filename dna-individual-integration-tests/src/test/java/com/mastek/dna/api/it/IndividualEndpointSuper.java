package com.mastek.dna.api.it;

import static com.mastek.dna.api.it.ValidationErrorMatcher.forValidationError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hamcrest.core.IsCollectionContaining;
import org.hamcrest.core.IsNot;
import org.hamcrest.core.IsSame;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.mastek.dna.api.ValidationError;
import com.mastek.dna.config.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = Application.class)
public class IndividualEndpointSuper
{
	@Autowired
	protected TestRestTemplate restTemplate;

	private final static Logger LOGGER = LoggerFactory.getLogger(IndividualEndpointSuper.class);

	protected <I, O> O send(final I toSend, final HttpMethod httpMethod, final Class<O> responseClass)
	{
		final ResponseEntity<O> response = restTemplate.exchange(getUrl(), httpMethod, new HttpEntity<I>(toSend), responseClass, getUrlVariables());
		final O responseObject = response.getBody();

		LOGGER.info("HttpStatus : {}", response.getStatusCode());
		LOGGER.info("Body : {}", responseObject);

		if (null != toSend)
		{
			Assert.assertThat("Response should not be the same object as request", responseObject, IsNot.not(IsSame.sameInstance(toSend)));
		}

		return response.getBody();
	}

	protected String getUrl()
	{
		return "/individual";
	}

	protected Map<String, Object> getUrlVariables()
	{
		return new HashMap<>();
	}

	protected void assertValidationError(final List<ValidationError> errors, final String field, final String message)
	{
		Assert.assertThat("Unexpected Validation Error", errors, IsCollectionContaining.hasItem(forValidationError(field, message)));
	}
}
