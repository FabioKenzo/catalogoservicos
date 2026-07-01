package br.com.kenzowebstudio.catalogoservicos.dto;

public class UsuarioDTO {
    private Long id; 
    private String nome; 
    private String email; 
    private String tipoPerfil;

    //construtor
    public UsuarioDTO(Long id, String nome, String email, String tipoPerfil) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.tipoPerfil = tipoPerfil;
    }

    //gets e sets
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

    public String getTipoPerfil() {
        return tipoPerfil;
    }

    public void setTipoPerfil(String tipoPerfil) {
        this.tipoPerfil = tipoPerfil;
    }
}



