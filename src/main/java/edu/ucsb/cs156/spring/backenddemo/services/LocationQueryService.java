package edu.ucsb.cs156.spring.backenddemo.services;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

/* GOAL: Location look up (enter a string, get back locations in the world along with their latitude/longitude) */

@Slf4j
@Service
public class LocationQueryService {

    ObjectMapper mapper = new ObjectMapper();

    private final RestTemplate restTemplate;

    public LocationQueryService(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    public static final String ENDPOINT = "https://nominatim.openstreetmap.org/search?q={location}&format=jsonv2";

    /*
     * This getJSON method encapsulates a web request to another web application
     * that is our source of information. Our backend is going to aggregate
     * information from many sources and then, eventually, make it all available to
     * a front-end user interface.
     */

    public String getJSON(String location) throws HttpClientErrorException {

        /* Debugging string: */
        log.info("location={}", location);

        /* Makes headers */
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        /* Declare entity & uriVariables */
        HttpEntity<String> entity = new HttpEntity<>(headers);
        Map<String, String> uriVariables = Map.of("location", location);

        /* Return */
        ResponseEntity<String> re = restTemplate.exchange(ENDPOINT,
                HttpMethod.GET, entity, String.class, uriVariables);

        return re.getBody();
    }
}

/*
 * FROM COUNTRY CODE:
 * 
 * Map<String, String> uriVariables = Map.of("country", country);
 * 
 * HttpEntity<String> entity = new HttpEntity<>("body", headers);
 * 
 * 
 * 
 * /*
 * FROM EARTHQUAKE
 * 
 * HttpEntity<String> entity = new HttpEntity<>(headers);
 * 
 * Map<String, String> uriVariables = Map.of("minMag", minMag, "distance",
 * distance, "latitude", ucsbLat, "longitude", ucsbLong);
 * 
 * 
 */