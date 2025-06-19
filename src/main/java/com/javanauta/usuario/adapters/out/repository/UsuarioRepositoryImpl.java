package com.javanauta.usuario.adapters.out.repository;

import com.javanauta.usuario.adapters.in.mapper.Converter;
import com.javanauta.usuario.adapters.out.entity.UsuarioEntity;
import com.javanauta.usuario.application.domain.UsuarioDomain;
import com.javanauta.usuario.porters.out.IUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UsuarioRepositoryImpl implements IUsuarioRepository {

    private final UsuarioJpaRepository jpaRepository;
    private final Converter converter;

    @Override
    //Retorna true/false caso o email existir
    public boolean existsByEmail(String email) {
        return jpaRepository.existsByEmail(email);
    }

    @Override
    //Salva o UsuarioDomain.
    public UsuarioDomain salvaUsuario(UsuarioDomain domain) {
        //Converto o UsuarioDomain para um UsuarioEntity
        UsuarioEntity entitySalvo = jpaRepository.save( //Salvo esse Usuario convertido.
                converter.paraEntity(domain));

        //Retorno o UsuarioDomain já salvo.
        return converter.paraDomain(entitySalvo);
    }

    @Override
    //Busca o email do usuario.
    public Optional<UsuarioDomain> findByEmail(String email) {
        return jpaRepository.findByEmail(email).map(converter::paraDomain);
    }

    @Override
    public void deleteByEmail(String email) {
        jpaRepository.deleteByEmail(email);
    }

    @Override
    public UsuarioDomain atualizaDadosUsuario(UsuarioDomain domain) {
        UsuarioEntity entitySalvo = jpaRepository.save(converter.paraEntity(domain));

        return converter.paraDomain(entitySalvo);
    }


}
