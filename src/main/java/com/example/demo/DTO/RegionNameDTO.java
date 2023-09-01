package com.example.demo.DTO;


public class RegionNameDTO {

    private String navn;

    public RegionNameDTO(String navn){
        this.navn = navn;
    }
    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }
}
