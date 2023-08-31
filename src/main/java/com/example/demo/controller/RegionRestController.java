package com.example.demo.controller;

import com.example.demo.model.Region;
import com.example.demo.service.ApiServiceGetRegioner;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RegionRestController {

    @Autowired
    ApiServiceGetRegioner apiServiceGetRegioner;

    @GetMapping("/getregioner")
    public List<Region> getRegioner(){
        List<Region> regionList = apiServiceGetRegioner.getRegioner();
        return regionList;
    }
    

}
