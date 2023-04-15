package com.example.ProyectoFinal_1.security;

import com.example.ProyectoFinal_1.domain.Usuario;
import com.example.ProyectoFinal_1.domain.UsuarioRol;
import com.example.ProyectoFinal_1.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CargadoraDeDatos implements ApplicationRunner {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder cifrador = new BCryptPasswordEncoder();
        String passwordACifrar = "1234";
        String passCifrada = cifrador.encode(passwordACifrar);
        Usuario usuarioAInsertar = new Usuario("Tomas","Casas","tomas@correo.com",passCifrada, UsuarioRol.ROLE_USER);
        usuarioRepository.save(usuarioAInsertar);
        usuarioRepository.save(new Usuario("Lina","Lopez","admin@correo.com",(cifrador.encode("admon1234")), UsuarioRol.ROLE_ADMIN));
    }
}
