package com.javanauta.usuario.adapters.out.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter // Adiciona o metodo Getter;
@Setter // Adiciona o metodo Setter;
@AllArgsConstructor // Construtor com todos os atributos como argumentos;
@NoArgsConstructor // Construtor sem nenhum argumento;
@Entity // Define como tabela;
@Table(name = "usuario") // Atribui um nome a tabela;
@Builder
public class UsuarioEntity implements UserDetails {
    //Criando o ID
    @Id // Definindo um ID
    @GeneratedValue(strategy = GenerationType.IDENTITY) //N° ID gerado automaticamente
    private Long id; // Todo ID é um tipo Long

    //Criando as colunas;
    @Column(name = "nome", length = 100) //Sempre vai vir acima do atributo que eu quero como coluna;
    private String nome;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "senha")
    private String senha;

    @OneToMany(cascade = CascadeType.ALL) //Um para muitos
    @JoinColumn(name = "usuario_id", referencedColumnName = "id") //Referencia pelo nome da coluna
    private List<EnderecoEntity> enderecoEntities; // Relacionamento entre tabelas

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private List<TelefoneEntity> telefoneEntities;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }
}
