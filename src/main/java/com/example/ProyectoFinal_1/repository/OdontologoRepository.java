package com.example.ProyectoFinal_1.repository;

import com.example.ProyectoFinal_1.domain.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OdontologoRepository extends JpaRepository<Odontologo,Long> {
}
