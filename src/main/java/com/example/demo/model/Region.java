package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Region {

        @Id
        @Column(length = 4)
        private String kode;
        private String navn;
        private String href;

        @OneToMany(mappedBy = "region", cascade = CascadeType.REMOVE)
        @JsonBackReference
        private Set<Kommune> kommuner = new HashSet<>();

    }
