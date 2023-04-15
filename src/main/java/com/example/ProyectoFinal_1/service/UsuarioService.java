package com.example.ProyectoFinal_1.service;

import com.example.ProyectoFinal_1.domain.Usuario;
import com.example.ProyectoFinal_1.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UsuarioService implements UserDetailsService {

    private UsuarioRepository usuarioRepository;
    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuarioBuscado = usuarioRepository.findByEmail(username);

        if(usuarioBuscado.isPresent()){
            return usuarioBuscado.get();
        } else {
            throw new UsernameNotFoundException("ERROR. Usuario con email " + username + " no fue encontrado en la Base de Datos");
        }
    }
}
