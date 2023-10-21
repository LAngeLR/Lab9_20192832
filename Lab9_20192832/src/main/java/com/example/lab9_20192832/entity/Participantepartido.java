package com.example.lab9_20192832.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "participantespartido")
@Getter
@Setter
public class Participantepartido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idparticipantesPartido", nullable = false)
    private Integer idParticipantesPartido;

    @ManyToOne
    @JoinColumn(name = "partido_idpartido", nullable = false)
    private Partido idPartido;

    @ManyToOne
    @JoinColumn(name = "participante_idparticipante", nullable = false)
    private Participante idParticipante;

    @Column(name = "horaFecha")
    private Date horaFecha;
}
