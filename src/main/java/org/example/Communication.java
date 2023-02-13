package org.example;

import com.sun.net.httpserver.Headers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


import java.util.*;
import java.util.List;
import java.util.Map;

@Component
public class Communication {

    @Autowired
    private RestTemplate restTemplate;

    private final String URL = "http://94.198.50.185:7081/api/users";

    private final String URL2 = "http://94.198.50.185:7081/api/users/3";


    public String getUsersHeaders(){
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.GET,
                null, new ParameterizedTypeReference<String>() { });  // null вместо тела реквеста, т.к. GET
        String users = responseEntity.getBody();
        String cookie = responseEntity.getHeaders().get("set-cookie").get(0);
        System.out.println(cookie);
        return cookie;
    }

    public void saveUser (User user, String cookie){

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cookie", cookie);
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.POST, entity, String.class);
        HttpHeaders hders = responseEntity.getHeaders();
        String body = responseEntity.getBody();
        System.out.println(body);
    }

    public void updateUser(User user, String cookie) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));        // работает и без этой строки
        headers.add("Cookie", cookie);
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.PUT, entity, String.class);
        HttpHeaders hders = responseEntity.getHeaders();
        String body = responseEntity.getBody();
        System.out.println(body);

    }

    public void deleteUser(String cookie) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cookie", cookie);
        HttpEntity<User> entity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL2, HttpMethod.DELETE, entity, String.class);
        HttpHeaders hders = responseEntity.getHeaders();
        String body = responseEntity.getBody();
        System.out.println(body);

    }


}
