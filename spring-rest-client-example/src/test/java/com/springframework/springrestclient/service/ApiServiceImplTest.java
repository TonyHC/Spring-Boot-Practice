package com.springframework.springrestclient.service;

import com.springframework.springrestclient.api.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ApiServiceImplTest {
    @Autowired
    ApiService apiService;

    @Test
    void getUsersTest() {
        List<User> users = apiService.getUsers(3);

        assertEquals(3, users.size());
    }
}