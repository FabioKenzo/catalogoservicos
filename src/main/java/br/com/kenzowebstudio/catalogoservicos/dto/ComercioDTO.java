package br.com.kenzowebstudio.catalogoservicos.dto;

public class ComercioDTO {

    private Long id; 
    private String nome; 
    private String categoria; 
    private String bairro; 
    private String telefone; 
    private String descricao; 
    private UsuarioDTO usuario;

    //construtor padrao
    public ComercioDTO(){

    }

    //construtor completo
    public ComercioDTO(br.com.kenzowebstudio.catalogoservicos.model.Comercios comercio){
        this.id = comercio.getId();
        this.nome = comercio.getNomeNegocio();
        this.categoria = comercio.getCategorias() != null ? comercio.getCategorias().getNomeCategoria() : null;
        this.bairro = comercio.getBairro();
        this.telefone = comercio.getWhatsapp();
        this.descricao = comercio.getDescricao();

        if(comercio.getUsuarios() != null){
            this.usuario = new UsuarioDTO(
                comercio.getUsuarios().getId(),
                comercio.getUsuarios().getNome(),
                comercio.getUsuarios().getEmail(),
                comercio.getUsuarios().getTipoPerfil()
            );
        }
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
