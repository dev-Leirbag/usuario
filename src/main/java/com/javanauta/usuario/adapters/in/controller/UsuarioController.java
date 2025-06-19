package com.javanauta.usuario.adapters.in.controller;

import com.javanauta.usuario.adapters.in.dto.request.UsuarioDtoRequest;
import com.javanauta.usuario.adapters.in.dto.request.UsuarioUpdateDtoRequest;
import com.javanauta.usuario.adapters.in.dto.response.UsuarioDtoResponse;
import com.javanauta.usuario.application.service.UsuarioServiceImpl;
import com.javanauta.usuario.application.service.ViaCepService;
import com.javanauta.usuario.application.infrastructure.clients.ViaCepDTO;
import com.javanauta.usuario.application.infrastructure.security.JwtUtil;
import com.javanauta.usuario.application.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Tag(name = "Usuario", description = "Faz o login e salva dados dos usuario")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class UsuarioController {

    private final UsuarioServiceImpl usuarioServiceImpl;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final ViaCepService viaCepService;

    @PostMapping
    @Operation(summary = "Salva o usuario", description = "Cria e salva dados do usuario")
    @ApiResponse(responseCode = "200", description = "Usuario cadastrado com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioDtoResponse> salvaUsuario(@RequestBody UsuarioDtoResponse usuarioDtoResponse){
        return ResponseEntity.ok(usuarioServiceImpl.salvaUsuario(usuarioDtoResponse));
    }

    @PostMapping("/login")
    @Operation(summary = "Faz o login do Usuario", description = "Login do usuario")
    @ApiResponse(responseCode = "200", description = "Login efetuado com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public String login(@RequestBody UsuarioDtoRequest dtoRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dtoRequest.getEmail(),
                        dtoRequest.getSenha())
        );
        return "Bearer " + jwtUtil.generateToken(authentication.getName());
    }

    @GetMapping
    @Operation(summary = "Busca dados do usuario por Email", description = "Busca dados do usuario por email")
    @ApiResponse(responseCode = "200", description = "Email encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioDtoResponse> buscaUsuarioPorEmail(@RequestParam("email") String email){
        return ResponseEntity.ok(usuarioServiceImpl.buscarUsuarioPorEmail(email));
    }

    @DeleteMapping("/{email}")
    @Operation(summary = "Deleta usuario por Email", description = "Deleta usuario por email")
    @ApiResponse(responseCode = "200", description = "Usuario deletado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "403", description = "Email não encontrado")
    public ResponseEntity<Void> deletaUsuarioPorEmail(@PathVariable String email){
        usuarioServiceImpl.deletaUsuarioPorEmaiil(email);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(summary = "Altera dados do Usuario", description = "Altera dados dos usuarios cadastrados")
    @ApiResponse(responseCode = "200", description = "Dados alterados")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "403", description = "Usuario não encontrado")
    @ApiResponse(responseCode = "401", description = "Usuario não autorizado")
    public ResponseEntity<UsuarioUpdateDtoRequest> atualizaDadosUsuario(@RequestBody UsuarioUpdateDtoRequest dto,
                                                                        @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(usuarioServiceImpl.atualizaDadosUsuario(dto, token));
    }

//    @PutMapping("/endereco")
//    @Operation(summary = "Altera dados de Endereço do Usuario", description = "Altera dados de endereço do usuario")
//    @ApiResponse(responseCode = "200", description = "Dados do endereço alterados")
//    @ApiResponse(responseCode = "500", description = "Erro de servidor")
//    @ApiResponse(responseCode = "403", description = "Endereço não encontrado")
//    public ResponseEntity<EnderecoDTO> atualizaEndereco(@RequestBody EnderecoDTO dto,
//                                                        @RequestParam("id") Long id){
//        return ResponseEntity.ok(usuarioServiceImpl.atualizaEndereco(id, dto));
//    }
//
//    @PutMapping("/telefone")
//    @Operation(summary = "Altera dados de Telefone do Usuario", description = "Altera dados de Telefone do usuario")
//    @ApiResponse(responseCode = "200", description = "Dados do telefone alterados")
//    @ApiResponse(responseCode = "500", description = "Erro de servidor")
//    @ApiResponse(responseCode = "403", description = "Telefone não encontrado")
//    public ResponseEntity<TelefoneDTO> atualizaTelefone(@RequestBody TelefoneDTO dto,
//                                                        @RequestParam("id") Long id){
//        return ResponseEntity.ok(usuarioServiceImpl.atualizaTelefone(id, dto));
//    }
//
//    @PostMapping("/endereco")
//    @Operation(summary = "Salva o endereço do Usuario", description = "Cria e salva o endereço do usuario")
//    @ApiResponse(responseCode = "200", description = "Endereço cadastrado com sucesso")
//    @ApiResponse(responseCode = "500", description = "Erro de servidor")
//    public ResponseEntity<EnderecoDTO> cadastraEndereco(@RequestBody EnderecoDTO dto,
//                                                        @RequestHeader("Authorization") String token) {
//        return ResponseEntity.ok(usuarioServiceImpl.cadastraEndereco(token, dto));
//    }
//
//    @PostMapping("/telefone")
//    @Operation(summary = "Salva o Telefone do Usuario", description = "Cria e salva o telefone do usuario")
//    @ApiResponse(responseCode = "200", description = "Telefone cadastrado com sucesso")
//    @ApiResponse(responseCode = "500", description = "Erro de servidor")
//    public ResponseEntity<TelefoneDTO> cadastraTelefone(@RequestBody TelefoneDTO dto,
//                                                        @RequestHeader("Authorization") String token){
//        return ResponseEntity.ok(usuarioServiceImpl.cadastraTelefone(token, dto));
//    }

    @GetMapping("/endereco/{cep}")
    @Operation(summary = "Busca dados de Endereço do Usuario via CEP ", description = "Busca dados de endereço do usuario via cep")
    @ApiResponse(responseCode = "200", description = "Dados encontrados")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<ViaCepDTO> buscarDadosCep(@PathVariable("cep") String cep){
        return ResponseEntity.ok(viaCepService.buscarDadosEndereco(cep));
    }

}
