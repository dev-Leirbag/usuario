package com.javanauta.usuario.adapters.out.repository.enderecorepository;

import com.javanauta.usuario.adapters.in.mapper.Converter;
import com.javanauta.usuario.adapters.out.entity.EnderecoEntity;
import com.javanauta.usuario.application.domain.EnderecoDomain;
import com.javanauta.usuario.porters.out.IEnderecoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EnderecoRepositoryImpl implements IEnderecoRepository {

    private final IEnderecoJpaRepository enderecoJpaRepository;
    private final Converter converter;

    @Override
    public EnderecoDomain cadastraEndereco(EnderecoDomain domain) {
        EnderecoEntity entitySalvo = enderecoJpaRepository.save(
                converter.paraEntity(domain));

        return converter.paraDomain(entitySalvo);
    }

    @Override
    public EnderecoDomain atualizarEndereco(EnderecoDomain domain) {
        EnderecoEntity entitySalvo = enderecoJpaRepository.save(
                converter.paraEntity(domain));

        return converter.paraDomain(entitySalvo);
    }

    @Override
    public Optional<EnderecoDomain> findById(Long id) {
        Optional<EnderecoDomain> enderecoDomain = enderecoJpaRepository.findById(id)
                .map(converter::paraDomain);
        return enderecoDomain;
    }
}
