package ar.edu.utn.frc.tup.lc.iv.services;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;

public interface HttpClient {
    <T> ResponseEntity<T> get(String url, ParameterizedTypeReference<T> responseType, Object... uriVariables);
}
