package com.javanauta.usuario.adapters.in.mapper;

import com.javanauta.usuario.adapters.in.dto.request.UsuarioDtoRequest;
import com.javanauta.usuario.adapters.in.dto.request.UsuarioUpdateDtoRequest;
import com.javanauta.usuario.adapters.in.dto.response.EnderecoDTO;
import com.javanauta.usuario.adapters.in.dto.response.TelefoneDTO;
import com.javanauta.usuario.adapters.in.dto.response.UsuarioDtoResponse;
import com.javanauta.usuario.adapters.out.entity.EnderecoEntity;
import com.javanauta.usuario.adapters.out.entity.TelefoneEntity;
import com.javanauta.usuario.adapters.out.entity.UsuarioEntity;
import com.javanauta.usuario.application.domain.EnderecoDomain;
import com.javanauta.usuario.application.domain.TelefoneDomain;
import com.javanauta.usuario.application.domain.UsuarioDomain;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface Converter {

    //Conversão de Usuario

    //Para Domain
    UsuarioDomain paraDomain(UsuarioDtoResponse dtoResponse); //Retorna um UsuarioDomain passando um UsuarioDtoResponse.
    UsuarioDomain paraDomain(UsuarioEntity entity); //Retorna um UsuarioDomain passando um UsuarioEntity.
    UsuarioDomain paraDomain(UsuarioDtoRequest dtoRequest); //Retorna um UsuarioDomain passando um UsuarioDtoRequest.
    UsuarioDomain paraDomain(UsuarioUpdateDtoRequest updateDtoRequest); //Retorna um UsuarioDomain passando um UsuarioUpdateDtoRequest.

    //Para Entity
    UsuarioEntity paraEntity(UsuarioDomain domain); //Retorna um UsuarioEntity passando um UsuarioDomain.
    UsuarioEntity paraEntity(UsuarioDtoResponse dtoResponse); //Retorna um UsuarioEntity passando um UsuarioDtoResponse.
    UsuarioEntity paraEntity(UsuarioDtoRequest dtoRequest); //Retorna um UsuarioEntity passando um UsuarioDtoRequest.
    UsuarioEntity paraEntity(UsuarioUpdateDtoRequest updateDtoRequest); //Retorna um UsuarioEntity passando um UsuarioUpdateDtoRequest.

    //Para DtoResponse
    UsuarioDtoResponse paraDtoResponse(UsuarioDomain domain); //Retorna um UsuarioDto passando um UsuarioDomain.
    UsuarioDtoResponse paraDtoResponse(UsuarioEntity entity); //Retorna um UsuarioDto passando um UsuarioEntity.
    UsuarioDtoResponse paraDtoResponse(UsuarioDtoRequest dtoRequest); //Retorna um UsuarioDtoResponse passando um UsuarioDtoRequest.
    UsuarioDtoResponse paraDtoRespone(UsuarioUpdateDtoRequest updateDtoRequest); //Retorna um UsuarioDtoResponse passando um UsuarioUpdateDtoRequest.

    //Para DtoRequest
    UsuarioDtoRequest paraDtoRequest(UsuarioDomain domain);//Retorna um UsuarioDtoRequest passando um UsuarioDomain.
    UsuarioDtoRequest paraDtoRequest(UsuarioEntity entity); //Retorna um UsuarioDtoRequest passando um UsuarioEntity.
    UsuarioDtoRequest paraDtoRequest(UsuarioDtoResponse dtoResponse); //Retorna um UsuarioDtoRequest passando um UsuarioDtoResponse.
    UsuarioDtoRequest paraDtoRequest(UsuarioUpdateDtoRequest updateDtoRequest); //Retorna um UsuarioDtoRequest passando um UsuarioUpdateDtoRequest.

    //Para UpdateDtoRequest
    UsuarioUpdateDtoRequest paraUpdateDto(UsuarioDomain domain); //Retorna um UsuarioUpdateDtoRequest passando um UsuarioDomain.
    UsuarioUpdateDtoRequest paraUpdateDto(UsuarioEntity entity); //Retorna um UsuarioUpdateDtoRequest passando um UsuarioEntity.
    UsuarioUpdateDtoRequest paraUpdateDto(UsuarioDtoResponse dtoResponse); //Retorna um UsuarioUpdateDtoRequest passando um UsuarioDtoResponse.
    UsuarioUpdateDtoRequest paraUpdateDto(UsuarioDtoRequest dtoRequest); //Retorna um UsuarioUpdateDtoRequest passando um UsuarioDtoRequest.


    //Conversão de Endereço

    //Para Domain
    EnderecoDomain paraDomain(EnderecoEntity entity); //Retorna um EnderecoDomain passando um EnderecoEntity.
    List<EnderecoDomain> paraListaD(List<EnderecoEntity> entity); //Retorna uma lista de EnderecoDomain,
    // passando uma lista de EnderecoEntity.
    List<EnderecoDomain> paraListaDo(List<EnderecoDTO> dtoList); //Retorna uma lista de EnderecoDomain,
    // passando uma lista de EnderecoDTO.
    EnderecoDomain paraDomain(EnderecoDTO dto); //Retorna um EnderecoDomain passando um EnderecoDTO.

    //Para Entity
    EnderecoEntity paraEntity(EnderecoDomain domain); //Retorna um EnderecoEntity passando um EnderecoDomain.
    List<EnderecoEntity> paraListaE(List<EnderecoDomain> domainList); //Retorna uma lista de EnderecoEntity,
    // passando uma lista de EnderecoDomain.
    List<EnderecoEntity> paraListaEn(List<EnderecoDTO> dtoList); //Retorna uma lista de EnderecoEntity,
    // passando uma lista de EnderecoDTO.
    EnderecoEntity paraEntity(EnderecoDTO dto); //Retorna um EnderecoEntity passando um EnderecoDTO.

    //Para DTO
    EnderecoDTO paraDtoResponse(EnderecoDomain domain); //Retorna um EnderecoDTO passando um EnderecoDomain.
    List<EnderecoDTO> paraListaDT(List<EnderecoDomain> domainList); //Retorna uma lista de EnderecoDTO,
    // passando uma lista de EnderecoDomain.
    List<EnderecoDTO> paraListaDTOo(List<EnderecoEntity> entityList); //Retorna uma lista de EnderecoDTO,
    // passando uma lista de EnderecoEntity.
    EnderecoDTO paraDtoResponse(EnderecoEntity entity); //Retorna um EnderecoDTO passando um EnderecoEntity.



//    public UsuarioEntity updateUsuario(UsuarioDTO usuarioDTO, UsuarioEntity entity) {
//        return UsuarioEntity.builder()
//                .nome(usuarioDTO.getNome() != null ? usuarioDTO.getNome() : entity.getNome())
//                .id(entity.getId())
//                .senha(usuarioDTO.getSenha() != null ? usuarioDTO.getSenha() : entity.getSenha())
//                .email(usuarioDTO.getEmail() != null ? usuarioDTO.getEmail() : entity.getEmail())
//                .enderecoEntities(entity.getEnderecoEntities())
//                .telefoneEntities(entity.getTelefoneEntities())
//                .build();
//    }
//
//    public EnderecoEntity updateEndereco(EnderecoDTO dto, EnderecoEntity entity){
//        return EnderecoEntity.builder()
//                .id(entity.getId())
//                .rua(dto.getRua() != null ? dto.getRua() : entity.getRua())
//                .numero(dto.getNumero() != null ? dto.getNumero() : entity.getNumero())
//                .cidade(dto.getCidade() != null ? dto.getCidade() : entity.getCidade())
//                .cep(dto.getCep() != null ? dto.getCep() : entity.getCep())
//                .complemento(dto.getComplemento() != null ? dto.getComplemento() : entity.getComplemento())
//                .estado(dto.getEstado() != null ? dto.getEstado() : entity.getEstado())
//                .build();
//    }
//
//    public TelefoneEntity updateTelefone(TelefoneDTO dto, TelefoneEntity entity){
//        return TelefoneEntity.builder()
//                .id(entity.getId())
//                .ddd(dto.getDdd() != null ? dto.getDdd() : entity.getDdd())
//                .numero(dto.getNumero() != null ? dto.getNumero() : entity.getNumero())
//                .build();
//    }

    // Conversão de Telefone

    //Para Domain
    TelefoneDomain paraDomain(TelefoneEntity entity); //Retorna um telefoneDomain passando um telefoneEntity.
    List<TelefoneDomain> paraListaDomain(List<TelefoneEntity> entityList); //Retorna uma lista de telefoneDomain,
    // passando uma lista de telefoneEntity.
    List<TelefoneDomain> paraListaDomainn(List<TelefoneDTO> dtoList); //Retorna uma lista de telefoneDomain,
    // passando uma lista de telefoneDTO
    TelefoneDomain paraDomain(TelefoneDTO dto); //Retorna um telefoneDomain passando um telefoneDTO;

    //Para Entity
    TelefoneEntity paraEntity(TelefoneDomain domain); //Retorna um telefoneEntity passando um telefoneDomain.
    List<TelefoneEntity> paraListaEntity(List<TelefoneDomain> domainList); //Retorna uma lista de telefoneEntity,
    // passando uma lista de telefoneDomain.
    List<TelefoneEntity> paraListaEntityy(List<TelefoneDTO> dtoList); //Retorna uma lista de telefoneEntity,
    // passando uma lista de telefoneDTO.
    TelefoneEntity paraEntity(TelefoneDTO dto); //Retorna um telefoneEntity passando um telefoneDTO.

    //Para DTO Response
    TelefoneDTO paraDtoResponse(TelefoneDomain domain); //Retorna um telefoneDTO passando um telefoneDomain.
    List<TelefoneDTO> paraListaDtoResponse(List<TelefoneDomain> domainList); //Retorna uma lista de telefoneDTO,
    // passando uma lista de telefoneDomain.
    List<TelefoneDTO> paraListaDtoResponsee(List<TelefoneEntity> entityList); //Retorna uma lista de telefoneDTO,
    // passando uma lista de telefoneEntity.
    TelefoneDTO paraDtoResponse(TelefoneEntity entity); //Retornna um telefoneDTO passando uma lista de telefoneEntity.

}
