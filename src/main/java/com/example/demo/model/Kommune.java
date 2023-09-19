package com.example.demo.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@NoArgsConstructor
@Getter
@Setter
@Entity
public class Kommune {

    @Id
    @Column(length = 4)
    private String kode;
    private String navn;
    private String href;
    private boolean isSlettet;

    @ManyToOne
    @JoinColumn(name = "region", referencedColumnName = "kode")
    Region region;

}
