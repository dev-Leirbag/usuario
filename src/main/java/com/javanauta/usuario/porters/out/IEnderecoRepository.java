package com.javanauta.usuario.porters.out;


import com.javanauta.usuario.application.domain.EnderecoDomain;

import java.util.Optional;

public interface IEnderecoRepository {

    EnderecoDomain cadastraEndereco (EnderecoDomain domain);

    EnderecoDomain atualizarEndereco (EnderecoDomain domain);

    Optional<EnderecoDomain> findById (Long id);
}
