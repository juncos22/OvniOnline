package com.dev.ovni.repos;

import com.dev.ovni.entities.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepo extends CrudRepository<Usuario, String> {
    Optional<Usuario> findByNombre(String nombre);
}
