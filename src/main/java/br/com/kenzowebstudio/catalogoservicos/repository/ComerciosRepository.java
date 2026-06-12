package br.com.kenzowebstudio.catalogoservicos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.kenzowebstudio.catalogoservicos.model.Comercios;

public interface ComerciosRepository extends JpaRepository<Comercios, Long> {

    List<Comercios> findByCategoriasNomeCategoriaAndBairro(String nomeCategoria, String bairro);


}
