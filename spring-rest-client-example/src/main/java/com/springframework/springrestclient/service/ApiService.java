package com.springframework.springrestclient.service;

import com.springframework.springrestclient.api.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ApiService {
    List<User> getUsers(Integer limit);
}