package com.example.lab9_20192832.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "participante")
@Getter
@Setter
public class Participante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idparticipante", nullable = false)
    private Integer idParticipante;

    @ManyToOne
    @JoinColumn(name = "equipo", nullable = false)
    private Equipo equipo;

    @Column(name = "carrera", length = 45)
    private String carrera;

    @Column(name = "codigo")
    private BigDecimal codigo;

    @Column(name = "tipoParticipante", length = 45)
    private String tipoParticipante;
}
