package br.com.kenzowebstudio.catalogoservicos.dto;

public class ServicoDTO {
    private Long id; 
    private String categoria; 
    private String bairro; 
    private String telefone; 
    private String descricao; 
    private UsuarioDTO usuario;

    //construtor
    public ServicoDTO(Long id, String categoria, String bairro, String telefone, String descricao, UsuarioDTO usuario) {
        this.id = id;
        this.categoria = categoria;
        this.bairro = bairro;
        this.telefone = telefone;
        this.descricao = descricao;
        this.usuario = usuario;
    }

    //gets e sets
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    

    
}
