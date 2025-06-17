package com.javanauta.usuario.porters.out;

import com.javanauta.usuario.application.domain.UsuarioDomain;
import jakarta.transaction.Transactional;

import java.util.Optional;

public interface IUsuarioRepository {

    boolean existsByEmail(String email);

    UsuarioDomain salvaUsuario(UsuarioDomain domain);

    Optional<UsuarioDomain> findByEmail(String email);

    @Transactional
    void deleteByEmail(String email);

}
