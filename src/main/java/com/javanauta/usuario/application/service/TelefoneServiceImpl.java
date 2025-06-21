package com.javanauta.usuario.application.service;

import com.javanauta.usuario.adapters.in.dto.response.TelefoneDTO;
import com.javanauta.usuario.adapters.in.mapper.Converter;
import com.javanauta.usuario.adapters.in.mapper.ConverterUpdate;
import com.javanauta.usuario.adapters.in.service.ITelefoneSerivce;
import com.javanauta.usuario.adapters.out.entity.TelefoneEntity;
import com.javanauta.usuario.application.domain.TelefoneDomain;
import com.javanauta.usuario.application.domain.UsuarioDomain;
import com.javanauta.usuario.application.infrastructure.exceptions.ResourceNotFoundException;
import com.javanauta.usuario.application.infrastructure.security.JwtUtil;
import com.javanauta.usuario.porters.out.ITelefoneRepository;
import com.javanauta.usuario.porters.out.IUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TelefoneServiceImpl implements ITelefoneSerivce {

    private final ITelefoneRepository telefoneRepository;
    private final IUsuarioRepository usuarioRepository;;
    private final Converter converter;
    private final ConverterUpdate converterUpdate;
    private final JwtUtil jwtUtil;

    @Override
    public TelefoneDTO cadastraTelefone(TelefoneDTO dto, String token) {
        String email = jwtUtil.extractUsername(token.substring(7));

        UsuarioDomain domain = usuarioRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("Email não encontrado " + email));

        TelefoneDomain domainTelefone = converter.paraDomain(dto, domain.getId());

        TelefoneDomain telefoneSalvo = telefoneRepository.cadastraTelefone(domainTelefone);

        return converter.paraDto(telefoneSalvo);
    }

    @Override
    public TelefoneDTO atualizaTelefone(TelefoneDTO dto, Long id) {
        TelefoneDomain domainId = telefoneRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Id do telefone não encontrado " + id));

        TelefoneEntity entity = converter.paraEntity(domainId);

        converterUpdate.updateTelefone(dto, entity);

        TelefoneDomain novoDomain = telefoneRepository.atualizarTelefone(
                converter.paraDomain(entity));

        return converter.paraDto(
                telefoneRepository.cadastraTelefone((novoDomain)));
    }
}
