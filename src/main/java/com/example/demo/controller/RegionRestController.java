package com.example.demo.controller;

import com.example.demo.DTO.RegionNameDTO;
import com.example.demo.model.Region;

import com.example.demo.service.ApiServiceGetRegioner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
public class RegionRestController {

    @Autowired
    ApiServiceGetRegioner apiServiceGetRegioner;

    @GetMapping("/getregioner")
    public List<Region> getRegioner() {
        List<Region> regionList = apiServiceGetRegioner.getRegioner();
        return regionList;
    }

    //Eksempel med DTO-klasse, kun få det udskrevet man ønsker
    /*
    @GetMapping("/getregionernavn")
    public List<RegionNameDTO> getRegioner() {
        List<Region> regionList = apiServiceGetRegioner.getRegioner();

        List<RegionNameDTO> regionNameDTOList = regionList.stream()
                .map(region -> new RegionNameDTO(region.getNavn()))
                .collect(Collectors.toList());

        return regionNameDTOList;
    }*/

    @PostMapping("/addregion")
    public ResponseEntity<Region> addRegion(@RequestBody Region region) {
        Region saveRegion = apiServiceGetRegioner.save(region);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveRegion);
    }

    @PutMapping("/updateregion/{kode}")
    public ResponseEntity<Region> updateRegion(@PathVariable String kode, @RequestBody Region updatedRegion) {
        Optional<Region> orgRegionOptional = apiServiceGetRegioner.findByKode(kode); // Antag, at du har en metode som findByKode i din service

        if (orgRegionOptional.isPresent()) {
            Region orgRegion = orgRegionOptional.get();

            orgRegion.setNavn(updatedRegion.getNavn());

            apiServiceGetRegioner.save(orgRegion);

            return ResponseEntity.ok(orgRegion);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/deleteregion/{kode}")
    public ResponseEntity<String> deleteRegion(@PathVariable String kode) {

        Optional<Region> existingRegionOptional = apiServiceGetRegioner.findByKode(kode);

        if (existingRegionOptional.isPresent()) {
            Region existingRegion = existingRegionOptional.get();

            apiServiceGetRegioner.delete(existingRegion);

            return ResponseEntity.ok("Region med kode " + kode + " er blevet slettet.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Region med kode " + kode + " blev ikke fundet i databasen.");
        }

    }
}
