package com.example.demo.service;


import com.example.demo.model.Kommune;
import com.example.demo.model.Region;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ApiServiceGetRegioner {

    List<Region> getRegioner();
    Optional<Region> findByKode(String kode);
    Region save(Region region);

    void delete(Region region);

    List<String> getKommuneNavne(Region region);

}
