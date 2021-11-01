package com.springframework.springrestclient.service;

import com.springframework.springrestclient.api.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class ApiServiceImpl implements ApiService {
    private static final String URL = "https://jsonplaceholder.typicode.com/users";

    private RestTemplate restTemplate;

    public ApiServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<User> getUsers(Integer limit) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .fromUriString(URL)
                .queryParam("_limit", limit);

        List<User> userData = restTemplate.getForObject(uriComponentsBuilder.toUriString(), List.class);

        return userData;
    }
}
