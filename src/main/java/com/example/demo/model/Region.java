package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class Region {
    @Id
    @Column(length = 4)
    private String kode;
    private String navn;
    private String href;

    @OneToMany(mappedBy = "region")
    @JsonBackReference
    private Set<Kommune> kommuner = new HashSet<>();
}
