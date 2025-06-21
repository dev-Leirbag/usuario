package com.javanauta.usuario.adapters.out.repository.telefonerepository;

import com.javanauta.usuario.adapters.in.mapper.Converter;
import com.javanauta.usuario.adapters.out.entity.TelefoneEntity;
import com.javanauta.usuario.application.domain.TelefoneDomain;
import com.javanauta.usuario.porters.out.ITelefoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TelefoneRepositoryImpl implements ITelefoneRepository {

    private final ITelefoneJpaRepository telefoneJpaRepository;
    private final Converter converter;

    @Override
    public TelefoneDomain cadastraTelefone(TelefoneDomain domain) {
        TelefoneEntity entity = converter.paraEntity(domain);

        TelefoneEntity entitySalvo = telefoneJpaRepository.save(entity);

        return converter.paraDomain(entitySalvo);
    }

    @Override
    public Optional<TelefoneDomain> findById(Long id) {
        Optional<TelefoneDomain> telefoneDomain = telefoneJpaRepository.findById(id)
                .map(converter::paraDomain);

        return telefoneDomain;
    }

    @Override
    public TelefoneDomain atualizarTelefone(TelefoneDomain domain) {
        TelefoneEntity entity = converter.paraEntity(domain);

        TelefoneEntity entitySalvo = telefoneJpaRepository.save(entity);

        return converter.paraDomain(entitySalvo);
    }
}
