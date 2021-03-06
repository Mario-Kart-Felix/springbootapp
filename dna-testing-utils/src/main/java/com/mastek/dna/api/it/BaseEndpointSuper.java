package com.mastek.dna.api.it;

import static com.mastek.dna.api.it.matcher.ValidationErrorMatcher.forValidationError;

import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dbunit.DatabaseUnitException;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsCollectionContaining;
import org.hamcrest.core.IsNot;
import org.hamcrest.core.IsSame;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mastek.dna.DnaTestingRuntimeException;
import com.mastek.dna.api.ValidationError;
import com.mastek.dna.db.DatabaseResetter;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public abstract class BaseEndpointSuper
{
	@Autowired
	protected TestRestTemplate restTemplate;

	@Autowired
	private DatabaseResetter databaseResetter;

	@Value("${api.username}")
	private String apiUsername;

	@Value("${api.password}")
	private String apiPassword;

	@Autowired
	protected JdbcTemplate jdbcTemplate;

	private final static Logger LOGGER = LoggerFactory.getLogger(BaseEndpointSuper.class);

	protected abstract String getUrl();

	@Before
	public void loadData() throws SQLException, DatabaseUnitException
	{
		databaseResetter.reset(getDataset());
	}

	protected String getDataset()
	{
		return "/data/dataset.xml";
	}

	protected <I, O> O send(final I toSend, final HttpMethod httpMethod, final Class<O> responseClass, final HttpStatus httpStatus)
	{
		final String json = getRequestJson(toSend);

		LOGGER.info("Request URL : [{}]", getUrl());
		LOGGER.info("URL Variable : [{}]", getUrlVariables());
		LOGGER.info("HttpMethod : [{}]", httpMethod);
		LOGGER.info("Request Body : [{}]", json);

		final ResponseEntity<O> response = restTemplate.exchange(getUrl(), httpMethod, new HttpEntity<String>(json, getHeaders()), responseClass, getUrlVariables());
		final O responseObject = response.getBody();

		LOGGER.info("HttpStatus : [{}]", response.getStatusCode());
		LOGGER.info("Body : [{}]", responseObject);

		Assert.assertThat("Unexpected HttpStatus", response.getStatusCode(), Is.is(httpStatus));

		if (null != toSend)
		{
			Assert.assertThat("Response should not be the same object as request", responseObject, IsNot.not(IsSame.sameInstance(toSend)));
		}

		return response.getBody();
	}

	private <I> String getRequestJson(final I toSend)
	{
		final ObjectMapper objMapper = getObjectMapper();
		String json = null;
		try
		{
			json = objMapper.writeValueAsString(toSend);
		}
		catch (final JsonProcessingException jpe)
		{
			throw new DnaTestingRuntimeException("Error creating JSON body", jpe);
		}
		return json;
	}

	protected ObjectMapper getObjectMapper()
	{
		return new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
	}

	protected boolean includeSecurity()
	{
		return true;
	}

	private MultiValueMap<String, String> getHeaders()
	{
		final HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

		if (includeSecurity())
		{
			addSecurityHeader(headers);
		}

		return headers;
	}

	private void addSecurityHeader(final HttpHeaders headers)
	{
		final Charset charset = Charset.forName("UTF-8");
		final byte[] encodedAuth = Base64.getEncoder().encode(
				String.format("%s:%s", apiUsername, apiPassword).getBytes(charset));

		headers.set("Authorization", "Basic " + new String(encodedAuth, charset));
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
