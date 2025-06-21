package com.javanauta.usuario.adapters.in.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UsuarioUpdateDtoRequest {

    private String nome;
    private String email;
    private String senha;

}
