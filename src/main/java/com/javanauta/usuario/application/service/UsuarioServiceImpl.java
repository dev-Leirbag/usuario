package com.javanauta.usuario.application.service;

import com.javanauta.usuario.adapters.in.dto.request.UsuarioUpdateDtoRequest;
import com.javanauta.usuario.adapters.in.dto.response.UsuarioDtoResponse;
import com.javanauta.usuario.adapters.in.mapper.Converter;
import com.javanauta.usuario.adapters.in.mapper.ConverterUpdate;
import com.javanauta.usuario.adapters.in.service.IUsuarioService;
import com.javanauta.usuario.adapters.out.entity.UsuarioEntity;
import com.javanauta.usuario.application.domain.UsuarioDomain;
import com.javanauta.usuario.application.infrastructure.exceptions.ConflictException;
import com.javanauta.usuario.application.infrastructure.exceptions.ResourceNotFoundException;
import com.javanauta.usuario.application.infrastructure.security.JwtUtil;
import com.javanauta.usuario.porters.out.IUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements IUsuarioService {

    private final IUsuarioRepository usuarioRepository;
    private final Converter converter;
    private final ConverterUpdate converterUpdate;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    //Metodo para salvar o usuario.
    public UsuarioDtoResponse salvaUsuario(UsuarioDtoResponse usuarioDtoResponse) {
        emailExiste(usuarioDtoResponse.getEmail()); // Verifica se o email do usuario já existe [true/false].

        usuarioDtoResponse.setSenha(passwordEncoder.encode(usuarioDtoResponse.getSenha()));//Faz a encriptação da senha do usuario.

        UsuarioDomain usuarioDomain = usuarioRepository.salvaUsuario( //Chama a repository para realizar o save do usuario.
                converter.paraDomain(usuarioDtoResponse)); //Realiza a conversão de um UsuarioDTO para um UsuarioDomain.

        return converter.paraDto(usuarioDomain); //Retorna um UsuarioDTO já salvo no banco de dados.
    }

    public void emailExiste(String email) {
        try {
            boolean emailExist = verificaEmail(email); //Retorna o email existente com true/false.
            if (emailExist) { //Retorna essa exceção caso o email já exista.
                throw new ConflictException("Esse email já esta cadastrado");
            }
        } catch (ConflictException e) {
            throw new ConflictException("Esse email já esta cadastrado");
        }
    }

    //Metodo para buscar o email
    public boolean verificaEmail(String email) {
        return usuarioRepository.existsByEmail(email); //Busca o email do usuario no banco de dados.
    }

    @Override
    //Metodo para buscar dados do usuario por email.
    public UsuarioDtoResponse buscarUsuarioPorEmail(String email) {
        try {
            //Converto para UsuarioDTO, passando como parametro o metodo da repository para buscar o email.
            return converter.paraDto(usuarioRepository.findByEmail(email).orElseThrow(
                    //Caso o email não existe, ira rodar essa exceção;
                    () -> new ResourceNotFoundException("Email não encontrado " + email)));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Email não encontrado " + email);
        }
    }

    @Override
    public void deletaUsuarioPorEmaiil(String email) {
        usuarioRepository.deleteByEmail(email);
    }

    @Override
    public UsuarioUpdateDtoRequest atualizaDadosUsuario(UsuarioUpdateDtoRequest updateDtoRequest, String token) {
        //Busco o email pelo token
        String email = jwtUtil.extractUsername(token.substring(7));

        //Criptografo a senha novamente.
        updateDtoRequest.setSenha(updateDtoRequest.getSenha() != null ? passwordEncoder.encode(updateDtoRequest.getSenha()) : null);

        //Transformando o email fornecido em um UsuarioDomain e buscando as informações desse email no BD.
        UsuarioDomain domain = usuarioRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("Email não encontrado " + email));

        //Converto o UsuarioDomain para UsuarioEntity
        UsuarioEntity entity = converter.paraEntity(domain);

        //Mesclo as informações
        converterUpdate.updateUsuario(updateDtoRequest, entity);

        //Converto para domain e chamo o metodo de atualizar na repository.
        UsuarioDomain domainAtualizado = usuarioRepository.atualizaDadosUsuario(converter.paraDomain(entity));

        //Retorno um UsuarioDTO já salvo
        return converter.paraUpdateDto(
                usuarioRepository.salvaUsuario(domainAtualizado));

    }
}
