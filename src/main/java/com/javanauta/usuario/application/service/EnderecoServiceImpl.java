package com.javanauta.usuario.application.service;

import com.javanauta.usuario.adapters.in.dto.response.EnderecoDTO;
import com.javanauta.usuario.adapters.in.mapper.Converter;
import com.javanauta.usuario.adapters.in.mapper.ConverterUpdate;
import com.javanauta.usuario.adapters.in.service.IEnderecoService;
import com.javanauta.usuario.adapters.out.entity.EnderecoEntity;
import com.javanauta.usuario.application.domain.EnderecoDomain;
import com.javanauta.usuario.application.domain.UsuarioDomain;
import com.javanauta.usuario.application.infrastructure.exceptions.ResourceNotFoundException;
import com.javanauta.usuario.application.infrastructure.security.JwtUtil;
import com.javanauta.usuario.porters.out.IEnderecoRepository;
import com.javanauta.usuario.porters.out.IUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnderecoServiceImpl implements IEnderecoService {

    private final IUsuarioRepository usuarioRepository;
    private final IEnderecoRepository enderecoRepository;
    private final Converter converter;
    private final ConverterUpdate converterUpdate;
    private final JwtUtil jwtUtil;

    @Override
    public EnderecoDTO cadastraEndereco(EnderecoDTO dto, String token) {
        String email = jwtUtil.extractUsername(token.substring(7));

        UsuarioDomain domain = usuarioRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("Email não encontrado " + email));

        EnderecoDomain domainEndereco = converter.paraDomain(dto, domain.getId());

        EnderecoDomain enderecoSalvo = enderecoRepository.cadastraEndereco(domainEndereco);

        return converter.paraDto(enderecoSalvo);
    }

    @Override
    public EnderecoDTO atualizaEndereco(EnderecoDTO dto, Long id) {
        EnderecoDomain domain = enderecoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Id do Endereço não encontrado " + id));

        EnderecoEntity entity = converter.paraEntity(domain);

        converterUpdate.updateEndereco(dto, entity);

        EnderecoDomain domainAtualizado =  enderecoRepository.atualizarEndereco(converter.paraDomain(entity));

        return converter.paraDto(
                enderecoRepository.cadastraEndereco(domainAtualizado));
    }
}
