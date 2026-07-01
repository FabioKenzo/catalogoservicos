package br.com.kenzowebstudio.catalogoservicos.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "comercios")
public class Comercios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "usuarios_id", nullable = false, unique = true)
    private Usuarios usuarios;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categorias categorias;

    @Column(name = "nome_negocio", nullable = false, length = 100)
    private String nomeNegocio;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(nullable = false, length = 100)
    private String bairro;

    @Column(nullable = false, length = 20)
    private String whatsapp;

    // construtor padrao jpa
    public Comercios() {

    }

    // construtor completo
    public Comercios(Long id, Usuarios usuarios, Categorias categorias, String nomeNegocio, String descricao,
            String bairro,
            String whatsapp) {
        this.id = id;
        this.usuarios = usuarios;
        this.categorias = categorias;
        this.nomeNegocio = nomeNegocio;
        this.descricao = descricao;
        this.bairro = bairro;
        this.whatsapp = whatsapp;
    }

    // gets e sets
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    public Categorias getCategorias() {
        return categorias;
    }

    public void setCategorias(Categorias categorias) {
        this.categorias = categorias;
    }

    public String getNomeNegocio() {
        return nomeNegocio;
    }

    public void setNomeNegocio(String nomeNegocio) {
        this.nomeNegocio = nomeNegocio;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }
}
