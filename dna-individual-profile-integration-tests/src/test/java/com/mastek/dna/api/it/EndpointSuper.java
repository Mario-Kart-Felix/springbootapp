package com.mastek.dna.api.it;

import static com.mastek.dna.api.it.matcher.ValidationErrorMatcher.forValidationError;

import java.nio.charset.Charset;
import java.util.Base64;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.MultiValueMap;

import com.mastek.dna.api.ValidationError;
import com.mastek.dna.config.IndividualProfileApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = { IndividualProfileApplication.class })
public abstract class EndpointSuper {
	@Autowired
	protected TestRestTemplate restTemplate;


	@Value("${api.username}")
	private String apiUsername;

	@Value("${api.password}")
	private String apiPassword;

	@Autowired
	protected JdbcTemplate jdbcTemplate;

	private final static Logger LOGGER = LoggerFactory.getLogger(EndpointSuper.class);

	protected abstract String getUrl();


	protected <I, O> O send(final I toSend, final HttpMethod httpMethod, final Class<O> responseClass) {
		final ResponseEntity<O> response = restTemplate.exchange(getUrl(), httpMethod,
				new HttpEntity<I>(toSend, getHeaders()), responseClass, getUrlVariables());
		final O responseObject = response.getBody();

		LOGGER.info("HttpStatus : {}", response.getStatusCode());
		LOGGER.info("Body : {}", responseObject);

		if (null != toSend) {
			Assert.assertThat("Response should not be the same object as request", responseObject,
					IsNot.not(IsSame.sameInstance(toSend)));
		}

		return response.getBody();
	}

	protected boolean includeSecurity() {
		return true;
	}

	private MultiValueMap<String, String> getHeaders() {
		final HttpHeaders headers = new HttpHeaders();

		if (includeSecurity()) {
			addSecurityHeader(headers);
		}

		return headers;
	}

	private void addSecurityHeader(final HttpHeaders headers) {
		final byte[] encodedAuth = Base64.getEncoder()
				.encode(String.format("%s:%s", apiUsername, apiPassword).getBytes(Charset.forName("UTF-8")));

		headers.set("Authorization", "Basic " + new String(encodedAuth));
	}

	protected Map<String, Object> getUrlVariables() {
		return new HashMap<>();
	}

	protected void assertValidationError(final List<ValidationError> errors, final String field, final String message) {
		Assert.assertThat("Unexpected Validation Error", errors,
				IsCollectionContaining.hasItem(forValidationError(field, message)));
	}
}
