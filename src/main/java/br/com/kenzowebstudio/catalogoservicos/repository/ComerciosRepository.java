package br.com.kenzowebstudio.catalogoservicos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.kenzowebstudio.catalogoservicos.model.Comercios;

public interface ComerciosRepository extends JpaRepository<Comercios, Long> {

    //busca todos os comercios do mais novo para o mais antigo
    List<Comercios> findAllByOrderByIdDesc();

    //bucsa por categoria e bairro
    List<Comercios> findByCategoriasNomeCategoriaAndBairro(String nomeCategoria, String bairro);


}
