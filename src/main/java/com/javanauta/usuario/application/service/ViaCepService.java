package com.javanauta.usuario.application.service;

import com.javanauta.usuario.application.infrastructure.clients.ViaCepClient;
import com.javanauta.usuario.application.infrastructure.clients.ViaCepDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ViaCepService {

    private final ViaCepClient client;

    public ViaCepDTO buscarDadosEndereco(String cep){
        return client.buscaDadosEndereco(processarCep(cep));
    }

    private String processarCep(String cep){
        String cepFormatado = cep.replace(" ","")
                .replace("-", "");

        if(!cepFormatado.matches("\\d+")
                || !Objects.equals(cepFormatado.length(), 8)){
            throw new IllegalArgumentException("O cep contem caracteres invalidos, favor verificar");
        }

        return cepFormatado;

    }
}
