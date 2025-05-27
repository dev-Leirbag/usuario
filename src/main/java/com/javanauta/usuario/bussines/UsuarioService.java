package com.javanauta.usuario.bussines;

import com.javanauta.usuario.bussines.converter.UsuarioConverter;
import com.javanauta.usuario.bussines.dto.EnderecoDTO;
import com.javanauta.usuario.bussines.dto.TelefoneDTO;
import com.javanauta.usuario.bussines.dto.UsuarioDTO;
import com.javanauta.usuario.infrastructure.entity.Endereco;
import com.javanauta.usuario.infrastructure.entity.Telefone;
import com.javanauta.usuario.infrastructure.entity.Usuario;
import com.javanauta.usuario.infrastructure.exceptions.ConflictException;
import com.javanauta.usuario.infrastructure.exceptions.ResourceNotFoundException;
import com.javanauta.usuario.infrastructure.repository.EnderecoRepository;
import com.javanauta.usuario.infrastructure.repository.TelefoneRepository;
import com.javanauta.usuario.infrastructure.repository.UsuarioRepository;
import com.javanauta.usuario.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final EnderecoRepository enderecoRepository;
    private final TelefoneRepository telefoneRepository;

    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO) {
        emailExiste(usuarioDTO.getEmail());
        usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
        Usuario usuario = usuarioConverter.paraUsuarioEntity(usuarioDTO);
        return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));
    }

    public void emailExiste(String email) {
        try {
            boolean emailExist = verificaEmail(email);
            if (emailExist) {
                throw new ConflictException("Esse email já esta cadastrado ");
            }
        } catch (ConflictException e) {
            throw new ConflictException("Esse email já esta cadastrado");
        }
    }

    public boolean verificaEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    public UsuarioDTO buscarUsuarioPorEmail(String email) {
        try{
            return usuarioConverter.paraUsuarioDTO(
                    usuarioRepository.findByEmail(email)
                            .orElseThrow(
                    () -> new ResourceNotFoundException("Emaiil não encontrado" + email)
                            )
            );
        }catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("Email não encontrado " + email);
        }
    }

    public void deletaUsuarioPorEmaiil(String email) {
        usuarioRepository.deleteByEmail(email);
    }

    public UsuarioDTO atualizaDadosUsuario(String token, UsuarioDTO dto){
        //Aqui buscamos o email do usuario atraves do token (TIRAR O OBRIGATORIEDADE DO EMAIL)
        String email = jwtUtil.extractUsername(token.substring(7));

        //Criptografia de senha
        dto.setSenha(dto.getSenha() != null ? passwordEncoder.encode(dto.getSenha()) : null);

        //Busca os dados do usuario no banco de dados
        Usuario usuarioEntity = usuarioRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("Email não encontrado"));

        //Mesclou os dados que recebemos na requisição DTO com os dados do banco de dados
        Usuario usuario = usuarioConverter.updateUsuario(dto, usuarioEntity);

        //Salvou os dados do usuario convertido e depois pegou o retorno e converteu para usuario dto
        return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));
    }

    public EnderecoDTO atualizaEndereco(Long idEndereco, EnderecoDTO enderecoDTO){
        Endereco entity = enderecoRepository.findById(idEndereco).orElseThrow(
                () -> new ResourceNotFoundException("ID não encontrado" + idEndereco));

        Endereco endereco = usuarioConverter.updateEndereco(enderecoDTO, entity);

        return usuarioConverter.paraEnderecoDTO(enderecoRepository.save(endereco));
    }

    public TelefoneDTO atualizaTelefone(Long idTelefone, TelefoneDTO telefoneDTO){
        Telefone entity = telefoneRepository.findById(idTelefone).orElseThrow(
                () -> new ResourceNotFoundException("ID não encontrado" + idTelefone));

        Telefone telefone = usuarioConverter.updateTelefone(telefoneDTO, entity);

        return usuarioConverter.paraTelefoneDTO(telefoneRepository.save(telefone));
    }

    public EnderecoDTO cadastraEndereco(String token, EnderecoDTO dto){
        String email = jwtUtil.extractUsername(token.substring(7));

        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("Email não encontrado " + email));

        Endereco endereco = usuarioConverter.paraEnderecoEntity(dto, usuario.getId());
        Endereco enderecoEntity = enderecoRepository.save(endereco);

        return usuarioConverter.paraEnderecoDTO(enderecoRepository.save(enderecoEntity));
    }

    public TelefoneDTO cadastraTelefone(String token, TelefoneDTO dto){
        String email = jwtUtil.extractUsername(token.substring(7));

        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("Email não encontrado " + email));

        Telefone telefone = usuarioConverter.paraTelefoneEntity(dto, usuario.getId());

        return usuarioConverter.paraTelefoneDTO(
                telefoneRepository.save(telefone)
        );
    }

}
