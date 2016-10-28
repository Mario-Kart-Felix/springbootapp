package com.mastek.dna.util;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;


@Component
public class ServiceUtils {
    private static final Logger LOG = LoggerFactory.getLogger(ServiceUtils.class);

    @Autowired
    private LoadBalancerClient loadBalancer;



    /**
     *
     * @param serviceId
     * @return
     */
    public URI getServiceUrl(String serviceId) {
    	
        URI uri = null;
        try {
            ServiceInstance instance = loadBalancer.choose(serviceId);

            if (instance == null) {
                throw new RuntimeException("Can't find a service with serviceId = " + serviceId);
            }

            uri = instance.getUri();
            LOG.info("Resolved serviceId '{}' to URL '{}'.", serviceId, uri);

        } catch (RuntimeException e) {
        	throw e;
        }

        return uri;
    }

	public HttpHeaders createHeaders(String username, String password) {
		HttpHeaders headers = new HttpHeaders();
		final byte[] encodedAuth = Base64.getEncoder()
				.encode(String.format("%s:%s", username, password).getBytes(Charset.forName("UTF-8")));

		headers.set("Authorization", "Basic " + new String(encodedAuth));
		headers.setContentType(MediaType.APPLICATION_JSON);

		return headers;
	}
}
