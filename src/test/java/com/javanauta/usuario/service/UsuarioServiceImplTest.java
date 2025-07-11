package com.javanauta.usuario.service;

import com.javanauta.usuario.adapters.in.dto.response.UsuarioDtoResponse;
import com.javanauta.usuario.adapters.in.mapper.Converter;
import com.javanauta.usuario.adapters.in.mapper.ConverterUpdate;
import com.javanauta.usuario.application.domain.UsuarioDomain;
import com.javanauta.usuario.application.infrastructure.exceptions.ConflictException;
import com.javanauta.usuario.application.infrastructure.security.JwtUtil;
import com.javanauta.usuario.application.service.UsuarioServiceImpl;
import com.javanauta.usuario.porters.out.IUsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UsuarioServiceImplTest {

    @Mock
    private  IUsuarioRepository usuarioRepository;

    @Mock
    private  Converter converter;

    @Mock
    private  ConverterUpdate converterUpdate;

    @Mock
    private  PasswordEncoder passwordEncoder;

    @Mock
    private  JwtUtil jwtUtil;

    @Autowired
    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Retorna com sucesso a criação do usuario")
    void salvaUsuario() {
        UsuarioDtoResponse dto = new UsuarioDtoResponse("Gabriel", "gabriel@gmail.com", "1234",null,null);
        UsuarioDomain usuarioDomain = new UsuarioDomain(null,"Gabriel", "gabriel@gmail.com", "encodedPassword", null, null);

        when(passwordEncoder.encode("1234")).thenReturn("encodedPassword");
        when(converter.paraDomain(dto)).thenReturn(usuarioDomain);
        when(usuarioRepository.salvaUsuario(usuarioDomain)).thenReturn(usuarioDomain);
        when(converter.paraDto(usuarioDomain)).thenReturn(dto);

        UsuarioDtoResponse rerponse = usuarioService.salvaUsuario(dto);

        assertEquals(dto.getNome(), rerponse.getNome());
        assertEquals(dto.getEmail(), rerponse.getEmail());
        assertEquals(dto.getSenha(), rerponse.getSenha());

        verify(passwordEncoder).encode("1234");
        verify(converter).paraDomain(dto);
        verify(usuarioRepository).salvaUsuario(usuarioDomain);
        verify(converter).paraDto(usuarioDomain);
    }

    @Test
    @DisplayName("Retorna uma Exception caso o email exista")
    void emailExistente(){
        String email = "teste@teste.com";

        when(usuarioRepository.existsByEmail(email)).thenReturn(true);

        ConflictException exception = assertThrows(ConflictException.class,
                () -> usuarioService.emailExiste(email));

        assertEquals("Esse email já esta cadastrado", exception.getMessage());

        verify(usuarioRepository).existsByEmail(email);
    }

    @Test
    @DisplayName("Não deve retornar uma Exception caso o email não exista")
    void emailNaoExiste(){
        String email = "naoExiste@gmail.com";

        when(usuarioRepository.existsByEmail(email)).thenReturn(false);

        assertDoesNotThrow(() -> usuarioService.emailExiste(email));

        verify(usuarioRepository).existsByEmail(email);
    }

    @Test
    @DisplayName("Deve retornar true se o email existir")
    void verificaEmailExiste(){
        String email = "teste@gmail.com";

        when(usuarioRepository.existsByEmail(email)).thenReturn(true);

        boolean result = usuarioService.verificaEmail(email);
        assertTrue(result);

        verify(usuarioRepository).existsByEmail(email);
    }

    @Test
    @DisplayName("Deve retornar false se o email não existir")
    void verificaEmailNaoExiste(){
        String email = "naoexiste@gmail.com";

        when(usuarioRepository.existsByEmail(email)).thenReturn(false);

        boolean result = usuarioService.verificaEmail(email);
        assertFalse(result);

        verify(usuarioRepository).existsByEmail(email);
    }
}