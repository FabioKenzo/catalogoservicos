package br.com.kenzowebstudio.catalogoservicos.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kenzowebstudio.catalogoservicos.model.Servicos;
import br.com.kenzowebstudio.catalogoservicos.model.Usuarios;
import br.com.kenzowebstudio.catalogoservicos.repository.ServicosRepository;
import br.com.kenzowebstudio.catalogoservicos.repository.UsuariosRepository;

@Service
public class ServicosService {

    @Autowired
    private ServicosRepository servicosRepository;

    @Autowired
    private UsuariosRepository usuariosRepository;

    public Servicos salvarOuAtualizar(String categoria, String bairro,String telefone, String descricao, Long usuarioId){

        //verifica se realmente existe no banco 
        Usuarios usuarios = usuariosRepository.findById(usuarioId)
        .orElseThrow(() -> new RuntimeException("Prestador não encontrado!"));

        //busca se ele ja possui servico cadastrado 
        Optional<Servicos> servicoExitente = servicosRepository.findByUsuariosId(usuarioId);

        Servicos servicos; 
        if(servicoExitente.isPresent()){
            servicos = servicoExitente.get(); //se ja existe atualiza 
        }else{
            servicos = new Servicos(); //se nao exite cria
            servicos.setUsuarios(usuarios); //vincula o usuario usando o metodo setUsuarios
        }

        //atualiza os dado com o que veio do form
        servicos.setCategoria(categoria);
        servicos.setBairro(bairro);
        servicos.setTelefone(telefone);
        servicos.setDescricao(descricao);

        return servicosRepository.save(servicos);
    }

    public java.util.List<Servicos> buscarServicos(String categoria, String bairro) {
    return servicosRepository.findByCategoriaContainingIgnoreCaseAndBairroContainingIgnoreCase(categoria, bairro);
}



}
