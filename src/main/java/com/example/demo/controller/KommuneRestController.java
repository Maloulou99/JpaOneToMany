package com.example.demo.controller;

import com.example.demo.model.Kommune;
import com.example.demo.model.Region;
import com.example.demo.service.ApiServiceGetKommuner;
import com.example.demo.service.ApiServiceGetRegioner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class KommuneRestController {

    @Autowired
    ApiServiceGetKommuner apiServiceGetKommuner;

    @GetMapping("/getkommuner")
    public List<Kommune> getKommuner() {
        List<Kommune> kommuneList = apiServiceGetKommuner.getKommuner();
        kommuneList = kommuneList.stream()
                .filter(kommune -> !kommune.isSlettet())
                .collect(Collectors.toList());
        return kommuneList;
    }


    //Eksempel med DTO-klasse, kun få det udskrevet man ønsker
   /* @GetMapping("/getkommunenavn")
    public List<RegionNameDTO> getRegioner() {
        List<Region> regionList = apiServiceGetRegioner.getRegioner();

        List<RegionNameDTO> regionNameDTOList = regionList.stream()
                .map(region -> new RegionNameDTO(region.getNavn()))
                .collect(Collectors.toList());

        return regionNameDTOList;
    }*/

    @PostMapping("/addkommune")
    public ResponseEntity<Kommune> addRegion(@RequestBody Kommune kommune) {
        Kommune saveKommune = apiServiceGetKommuner.save(kommune);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveKommune);
    }

    @PutMapping("/updatekommune/{kode}")
    public ResponseEntity<Kommune> updateRegion(@PathVariable String kode, @RequestBody Kommune updatedKommune) {
        Optional<Kommune> orgRegionOptional = apiServiceGetKommuner.findByKode(kode);
        if (orgRegionOptional.isPresent()) {
            Kommune orgKommune = orgRegionOptional.get();

            orgKommune.setNavn(updatedKommune.getNavn());

            apiServiceGetKommuner.save(orgKommune);

            return ResponseEntity.ok(orgKommune);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/deletekommune/{kode}")
    public ResponseEntity<String> deleteKommune(@PathVariable String kode) {

        Optional<Kommune> existingKommuneOptional = apiServiceGetKommuner.findByKode(kode);

        if (existingKommuneOptional.isPresent()) {
            Kommune existingKommune = existingKommuneOptional.get();

            apiServiceGetKommuner.delete(existingKommune);

            return ResponseEntity.ok("Kommune med kode " + kode + " er blevet slettet.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Kommune med kode " + kode + " blev ikke fundet i databasen.");
        }
    }



}


