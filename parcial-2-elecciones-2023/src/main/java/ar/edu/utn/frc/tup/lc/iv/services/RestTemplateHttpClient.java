package ar.edu.utn.frc.tup.lc.iv.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestTemplateHttpClient implements HttpClient {
    private final RestTemplate restTemplate;

    @Autowired
    public RestTemplateHttpClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public <T>ResponseEntity<T> get(String url, ParameterizedTypeReference<T> responseType, Object... uriVariables) {
        return restTemplate.exchange(url, HttpMethod.GET, null, responseType, uriVariables);
    }
}
