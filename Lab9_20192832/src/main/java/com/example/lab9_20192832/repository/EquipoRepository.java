package com.example.lab9_20192832.repository;

import com.example.lab9_20192832.entity.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipoRepository  extends JpaRepository<Equipo, Integer> {
}
