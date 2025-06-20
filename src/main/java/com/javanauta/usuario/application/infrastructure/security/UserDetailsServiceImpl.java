package com.javanauta.usuario.application.infrastructure.security;


import com.javanauta.usuario.adapters.out.repository.usuariorepository.UsuarioRepositoryImpl;
import com.javanauta.usuario.application.domain.UsuarioDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    // Repositório para acessar dados de usuário no banco de dados
    @Autowired
    private UsuarioRepositoryImpl usuarioRepository;

    // Implementação do método para carregar detalhes do usuário pelo e-mail
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Busca o usuário no banco de dados pelo e-mail
        UsuarioDomain domain = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + email));

        // Cria e retorna um objeto UserDetails com base no usuário encontrado
        return org.springframework.security.core.userdetails.User
                .withUsername(domain.getEmail()) // Define o nome de usuário como o e-mail
                .password(domain.getSenha()) // Define a senha do usuário
                .build(); // Constrói o objeto UserDetails
    }
}
