package com.javanauta.usuario.adapters.in.service;

import com.javanauta.usuario.adapters.in.dto.response.TelefoneDTO;

public interface ITelefoneSerivce {

    TelefoneDTO cadastraTelefone(TelefoneDTO dto, String token);

    TelefoneDTO atualizaTelefone(TelefoneDTO dto, Long id);
}
