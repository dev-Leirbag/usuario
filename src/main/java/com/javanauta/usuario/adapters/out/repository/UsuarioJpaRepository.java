package com.javanauta.usuario.adapters.out.repository;

import com.javanauta.usuario.adapters.out.entity.UsuarioEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioJpaRepository extends JpaRepository<UsuarioEntity, Long> {

    boolean existsByEmail(String email);

    Optional<UsuarioEntity> findByEmail(String email);

    @Transactional
    void deleteByEmail(String email);



}
