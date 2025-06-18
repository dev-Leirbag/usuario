package com.javanauta.usuario.adapters.in.dto.request;

import com.javanauta.usuario.adapters.in.dto.response.EnderecoDTO;
import com.javanauta.usuario.adapters.in.dto.response.TelefoneDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDtoRequest {

    private String email;
    private String senha;

}
