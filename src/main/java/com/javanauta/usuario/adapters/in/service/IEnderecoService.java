package com.javanauta.usuario.adapters.in.service;

import com.javanauta.usuario.adapters.in.dto.response.EnderecoDTO;

public interface IEnderecoService {

    EnderecoDTO cadastraEndereco (EnderecoDTO dto, String token);

    EnderecoDTO atualizaEndereco(EnderecoDTO dto, Long id);

}
