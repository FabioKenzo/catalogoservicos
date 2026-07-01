package br.com.kenzowebstudio.catalogoservicos.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kenzowebstudio.catalogoservicos.model.Categorias;
import br.com.kenzowebstudio.catalogoservicos.repository.CategoriasRepository;

@Service
public class CategoriasService {

    @Autowired
    private CategoriasRepository categoriasRepository;

    //listar todas as categorias
    public List<Categorias> listarTodas(){
        return categoriasRepository.findAll();
    }

    //buscar uma categoria por id
    public Categorias buscarPorId(Long id){
        return categoriasRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Categoria não encontrada com o ID: " + id));
    }

    
    public Categorias buscarPorNome(String nomeCategoria) {
        return categoriasRepository.findByNomeCategoria(nomeCategoria)
        .orElseThrow(() -> new RuntimeException("Categoria não encontrada com o nome: " + nomeCategoria));
    }

    public Categorias salvar(Categorias categorias){
        return categoriasRepository.save(categorias);
    }
}
