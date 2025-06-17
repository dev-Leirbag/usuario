package com.javanauta.usuario.adapters.in.service;

import com.javanauta.usuario.adapters.in.dto.UsuarioDTO;

import java.util.Optional;

public interface IUsuarioService {

    UsuarioDTO salvaUsuario(UsuarioDTO dto);

    UsuarioDTO buscarUsuarioPorEmail(String email);

    void deletaUsuarioPorEmaiil (String email);

    UsuarioDTO atualizaDadosUsuario (UsuarioDTO dto, String token);

}
