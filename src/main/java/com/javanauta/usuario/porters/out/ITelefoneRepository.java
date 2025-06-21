package com.javanauta.usuario.porters.out;


import com.javanauta.usuario.application.domain.TelefoneDomain;

import java.util.Optional;

public interface ITelefoneRepository {

    TelefoneDomain cadastraTelefone(TelefoneDomain domain);

    Optional<TelefoneDomain> findById(Long id);

    TelefoneDomain atualizarTelefone (TelefoneDomain domain);

}
