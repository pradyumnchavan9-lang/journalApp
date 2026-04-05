package com.journalentry.JournalEntry.service;

import com.journalentry.JournalEntry.api.WeatherResponse;
import com.journalentry.JournalEntry.cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class WeatherService {


        @Value("${weather.api.key}")
        private String apiKey;

        @Autowired
        private final RestTemplate restTemplate;

        @Autowired
        private  AppCache appCache;

        @Autowired
        private RedisService redisService;

        public WeatherService(RestTemplate restTemplate) {
            this.restTemplate = restTemplate;
        }

    public WeatherResponse getWeather(String city){
            WeatherResponse weatherResponse = redisService.get(city,WeatherResponse.class);
            if(weatherResponse != null){
                return weatherResponse;
            }else{


                String finalAPI = appCache.appCache.get(AppCache.keys.WEATHER_API.toString()).replace("CITY",city).replace("<api_key>",apiKey);
                ResponseEntity<WeatherResponse> response = restTemplate.exchange(
                        finalAPI,
                        HttpMethod.GET,
                        null,
                        WeatherResponse.class
                );

                WeatherResponse body = response.getBody();
                if(body != null){
                    redisService.set(city,body,300l);
                }
                return body;

            }

        }

}
