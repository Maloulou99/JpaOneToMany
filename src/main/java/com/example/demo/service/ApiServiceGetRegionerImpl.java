package com.example.demo.service;

import com.example.demo.Repository.RegionRepository;
import com.example.demo.model.Kommune;
import com.example.demo.model.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class  ApiServiceGetRegionerImpl implements ApiServiceGetRegioner {

    private final RestTemplate restTemplate;

    public ApiServiceGetRegionerImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    RegionRepository regionRepository;

    private void saveRegioner(List<Region> regioner) {
        regioner.forEach(reg -> regionRepository.save(reg));
    }


    String regionUrl = "https://api.dataforsyningen.dk/regioner";

    @Override
    public List<Region> getRegioner() {
        ResponseEntity<List<Region>> regionResponse =
                restTemplate.exchange(regionUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<Region>>(){
                });
        List<Region> regioner = regionResponse.getBody();
        saveRegioner(regioner);
        return regioner; }

    @Override
    public Optional<Region> findByKode(String kode) {
        return regionRepository.findById(kode);
    }

    @Override
    public Region save(Region region) {
        return regionRepository.save(region);
    }

    @Override
    public void delete(Region region) {
        regionRepository.delete(region);
    }

    @Override
    public List<String> getKommuneNavne(Region region) {
        return region.getKommuner().stream()
                .map(Kommune::getNavn)
                .collect(Collectors.toList());
    }



}
