package br.com.kenzowebstudio.catalogoservicos.dto;

public class ServicoDTO {
    private Long id;
    private String categoria;
    private String bairro;
    private String telefone;
    private String descricao;
    private UsuarioDTO usuario;

    //construtor
    public ServicoDTO(br.com.kenzowebstudio.catalogoservicos.model.Servicos servico) {
        this.id = servico.getId();
        this.categoria = servico.getCategoria();
        this.bairro = servico.getBairro();
        this.telefone = servico.getTelefone();
        this.descricao = servico.getDescricao();

        //mapeia a entidade Usuarios para o UsuarioDTO limpo
        if (servico.getUsuarios() != null) {
            this.usuario = new UsuarioDTO(
                    servico.getUsuarios().getId(),
                    servico.getUsuarios().getNome(),
                    servico.getUsuarios().getEmail(),
                    servico.getUsuarios().getTipoPerfil());
        }
    }

    // gets e sets
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
