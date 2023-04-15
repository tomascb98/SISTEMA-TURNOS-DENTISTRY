package com.example.ProyectoFinal_1.repository;

import com.example.ProyectoFinal_1.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Optional<Usuario> findByEmail(String email); //select * from usuarios where email = ?
}
