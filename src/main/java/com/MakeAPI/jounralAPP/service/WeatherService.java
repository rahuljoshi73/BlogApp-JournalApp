package com.MakeAPI.jounralAPP.service;

import com.MakeAPI.jounralAPP.Api_response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class WeatherService {

    @Autowired
    private RestTemplate restTemplate;


}
