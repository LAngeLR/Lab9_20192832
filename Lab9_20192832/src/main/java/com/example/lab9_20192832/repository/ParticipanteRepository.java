package com.example.lab9_20192832.repository;

import com.example.lab9_20192832.entity.Participante;
import com.example.lab9_20192832.entity.Participantepartido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipanteRepository extends JpaRepository<Participante, Integer> {

    @Query(value ="select * from participante where equipo =?1", nativeQuery = true )
    List<Participante> listaParticipantesEquipo(int equipo);
}
