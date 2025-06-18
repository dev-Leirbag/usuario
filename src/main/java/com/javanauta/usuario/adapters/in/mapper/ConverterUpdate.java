package com.javanauta.usuario.adapters.in.mapper;

import com.javanauta.usuario.adapters.in.dto.response.EnderecoDTO;
import com.javanauta.usuario.adapters.in.dto.response.TelefoneDTO;
import com.javanauta.usuario.adapters.in.dto.response.UsuarioDtoResponse;
import com.javanauta.usuario.adapters.out.entity.EnderecoEntity;
import com.javanauta.usuario.adapters.out.entity.TelefoneEntity;
import com.javanauta.usuario.adapters.out.entity.UsuarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ConverterUpdate {

    void updateUsuario(UsuarioDtoResponse dto, @MappingTarget UsuarioEntity entity);

    void updateEndereco(EnderecoDTO dto, @MappingTarget EnderecoEntity entity);

    void updateTelefone(TelefoneDTO dto, @MappingTarget TelefoneEntity entity);
}
