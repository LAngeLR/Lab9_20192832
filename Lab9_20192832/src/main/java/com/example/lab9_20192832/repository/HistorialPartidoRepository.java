package com.example.lab9_20192832.repository;

import com.example.lab9_20192832.entity.Historialpartido;
import com.example.lab9_20192832.entity.Participantepartido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface HistorialPartidoRepository extends JpaRepository<Historialpartido, Integer> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO historialPartidos (partido_idpartido, deporte_iddeporte, horaFecha) VALUES (?1, ?2, ?3)", nativeQuery = true)
    void crearHistorial(int partido, int deporte, Date hora);

    @Query(value = "SELECT * FROM historialPartidos h \n" +
            "inner join partido p on h.partido_idpartido =p.idpartido \n" +
            "inner join equipo ea on p.equipoA = ea.idequipo\n" +
            "inner join equipo eb on p.equipoB = eb.idequipo\n" +
            "\n" +
            "where ea.idequipo = ?1 or eb.idequipo=?1", nativeQuery = true)
    List<Historialpartido> buscarEquipoHistorial(Integer idEquipo);


}
