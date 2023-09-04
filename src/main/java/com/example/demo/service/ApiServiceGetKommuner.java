package com.example.demo.service;

import com.example.demo.model.Kommune;
import com.example.demo.model.Region;

import java.util.List;
import java.util.Optional;

public interface ApiServiceGetKommuner {
    List<Kommune> getKommuner();

    Optional<Kommune> findByKode(String kode);
    Kommune save(Kommune kommune);

    void delete(Kommune kommune);
    boolean isDeleted(Kommune kommune);
}
