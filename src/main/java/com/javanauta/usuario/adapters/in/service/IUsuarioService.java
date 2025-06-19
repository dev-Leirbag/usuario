package com.javanauta.usuario.adapters.in.service;

import com.javanauta.usuario.adapters.in.dto.request.UsuarioUpdateDtoRequest;
import com.javanauta.usuario.adapters.in.dto.response.UsuarioDtoResponse;

public interface IUsuarioService {

    UsuarioDtoResponse salvaUsuario(UsuarioDtoResponse dto);

    UsuarioDtoResponse buscarUsuarioPorEmail(String email);

    void deletaUsuarioPorEmaiil (String email);

    UsuarioUpdateDtoRequest atualizaDadosUsuario (UsuarioUpdateDtoRequest updateDtoRequest, String token);

}
