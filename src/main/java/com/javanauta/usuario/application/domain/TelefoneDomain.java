package com.javanauta.usuario.application.domain;

public class TelefoneDomain {
    //Atributos
    private Long id;
    private String numero;
    private String ddd;
    private Long usuario_id;

    //Construtor com todos os atributos

    public TelefoneDomain(Long id, String numero, String ddd, Long usuario_id) {
        this.id = id;
        this.numero = numero;
        this.ddd = ddd;
        this.usuario_id = usuario_id;
    }

    //Construto sem atributos

    public TelefoneDomain() {
    }

    //Metodos getters e setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public Long getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(Long usuario_id) {
        this.usuario_id = usuario_id;
    }
}
