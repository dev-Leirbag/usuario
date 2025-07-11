package com.javanauta.usuario.adapters.in.service;

import com.javanauta.usuario.adapters.in.dto.request.UsuarioDtoRequest;
import com.javanauta.usuario.adapters.in.dto.request.UsuarioUpdateDtoRequest;
import com.javanauta.usuario.adapters.in.dto.response.UsuarioDtoResponse;
import com.javanauta.usuario.application.infrastructure.exceptions.UnauthorizedException;

public interface IUsuarioService {

    UsuarioDtoResponse salvaUsuario(UsuarioDtoResponse dto);

    String autenticarUsuario (UsuarioDtoRequest usuarioDtoRequest) throws UnauthorizedException;

    UsuarioDtoResponse buscarUsuarioPorEmail(String email);

    void deletaUsuarioPorEmaiil (String email);

    UsuarioUpdateDtoRequest atualizaDadosUsuario (UsuarioUpdateDtoRequest updateDtoRequest, String token);

}
