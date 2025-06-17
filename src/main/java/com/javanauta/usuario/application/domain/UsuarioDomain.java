package com.javanauta.usuario.application.domain;

import java.util.List;


public class UsuarioDomain{
    //Atributos
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private List<EnderecoDomain> enderecos;
    private List<TelefoneDomain> telefones;

    //Construtor com todos os atributos
    public UsuarioDomain(Long id, String nome, String email, String senha,
                         List<EnderecoDomain> enderecos, List<TelefoneDomain> telefones) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.enderecos = enderecos;
        this.telefones = telefones;
    }

    //Construtor sem atributos
    public UsuarioDomain() {
    }

    //Metodos getters e setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<EnderecoDomain> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<EnderecoDomain> enderecos) {
        this.enderecos = enderecos;
    }

    public List<TelefoneDomain> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<TelefoneDomain> telefones) {
        this.telefones = telefones;
    }
}
