package com.example.demo.Repository;


import com.example.demo.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegionRepository extends JpaRepository<Region, String> {

}
