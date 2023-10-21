package com.example.lab9_20192832.repository;

import com.example.lab9_20192832.entity.Partido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartidoRepository extends JpaRepository<Partido, Integer> {

}
