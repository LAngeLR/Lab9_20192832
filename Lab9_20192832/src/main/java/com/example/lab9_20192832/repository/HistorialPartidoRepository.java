package com.example.lab9_20192832.repository;

import com.example.lab9_20192832.entity.Historialpartido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
public interface HistorialPartidoRepository extends JpaRepository<Historialpartido, Integer> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO historialPartidos (partido_idpartido, deporte_iddeporte, horaFecha) VALUES (?1, ?2, ?3)", nativeQuery = true)
    void crearHistorial(int partido, int deporte, Date hora);


}
