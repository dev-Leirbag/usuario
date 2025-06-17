package com.javanauta.usuario.porters.out;


import com.javanauta.usuario.adapters.out.entity.TelefoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelefoneRepository extends JpaRepository<TelefoneEntity, Long> {
}
