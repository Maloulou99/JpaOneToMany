package com.example.demo.service;

import com.example.demo.Repository.KommuneRespository;
import com.example.demo.Repository.RegionRepository;
import com.example.demo.model.Kommune;
import com.example.demo.model.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ApiServiceGetKommunerImpl implements ApiServiceGetKommuner{

    private final RestTemplate restTemplate;

    public ApiServiceGetKommunerImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    KommuneRespository kommuneRespository;

    private void saveKommune(List<Kommune> kommuner) {
        kommuner.forEach(reg -> kommuneRespository.save(reg));
    }

    String kommuneUrl = "https://api.dataforsyningen.dk/kommuner";

    @Override
    public List<Kommune> getKommuner() {
        ResponseEntity<List<Kommune>> kommuneResponse =
                restTemplate.exchange(kommuneUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<Kommune>>(){
                });
        List<Kommune> kommuner = kommuneResponse.getBody();
        saveKommune(kommuner);
        return kommuner;
    }

}


