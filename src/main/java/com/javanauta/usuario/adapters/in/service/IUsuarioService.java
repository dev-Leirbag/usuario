package com.javanauta.usuario.adapters.in.service;

import com.javanauta.usuario.adapters.in.dto.response.UsuarioDtoResponse;

public interface IUsuarioService {

    UsuarioDtoResponse salvaUsuario(UsuarioDtoResponse dto);

    UsuarioDtoResponse buscarUsuarioPorEmail(String email);

    void deletaUsuarioPorEmaiil (String email);

    UsuarioDtoResponse atualizaDadosUsuario (UsuarioDtoResponse dto, String token);

}
