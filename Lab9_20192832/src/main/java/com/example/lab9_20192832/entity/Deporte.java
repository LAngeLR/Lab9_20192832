package com.example.lab9_20192832.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "deporte")
@Getter
@Setter
public class Deporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddeporte", nullable = false)
    private Integer idDeporte;

    @Column(name = "nombreDeporte", nullable = false, length = 45)
    private String nombreDeporte;

    @Column(name = "pesoDeporte")
    private Integer pesoDeporte;

}
