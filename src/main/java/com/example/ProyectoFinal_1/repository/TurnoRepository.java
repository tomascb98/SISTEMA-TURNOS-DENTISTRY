package com.example.ProyectoFinal_1.repository;

import com.example.ProyectoFinal_1.domain.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnoRepository extends JpaRepository<Turno,Long> {
}
