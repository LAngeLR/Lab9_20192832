package com.example.lab9_20192832.repository;

import com.example.lab9_20192832.entity.Participantepartido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface ParticipantePartidoRepository extends JpaRepository<Participantepartido, Integer> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO participantespartido (partido_idpartido, participante_idparticipante, horaFecha) VALUES (?1, ?2, ?3)", nativeQuery = true)
    void crearPartixPartido(int partido, int participante, Date hora);

    @Modifying
    @Transactional
    @Query(value="ALTER TABLE participantespartido MODIFY COLUMN idParticipantesPartido INT AUTO_INCREMENT", nativeQuery=true)
    void contadortabla();

    @Query(value = "SELECT p.idParticipantesPartido, p.partido_idpartido, p.participante_idparticipante, p.horaFecha FROM participantespartido p inner join participante u on p.participante_idparticipante=u.idparticipante where u.equipo=?1", nativeQuery = true)
    List<Participantepartido> buscarEquipo(Integer idEquipo);

}
