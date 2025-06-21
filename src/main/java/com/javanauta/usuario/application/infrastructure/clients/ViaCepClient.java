package com.javanauta.usuario.application.infrastructure.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//Se comporta como se fosse um Postman, onde ele faz a requisição no endpoint busca usuario por email
// passando email e token.

@FeignClient(name = "via-cep", url = "${viacep.url}")
public interface ViaCepClient {

    @GetMapping("/ws/{cep}/json/")
    ViaCepDTO buscaDadosEndereco(@PathVariable("cep") String cep);
}
