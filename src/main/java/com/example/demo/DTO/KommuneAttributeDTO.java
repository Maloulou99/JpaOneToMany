package com.example.demo.DTO;

import com.example.demo.model.Region;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class KommuneAttributeDTO {
    private String kode;
    private String navn;
    private String href;
    @ManyToOne
    @JoinColumn(name = "region", referencedColumnName = "kode")
    Region region;


    public KommuneAttributeDTO(String kode, String navn, String href, Region region) {
        this.kode = kode;
        this.navn = navn;
        this.href = href;
        this.region = region;
    }


    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}

