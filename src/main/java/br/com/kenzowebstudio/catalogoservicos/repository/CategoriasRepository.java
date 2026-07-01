package br.com.kenzowebstudio.catalogoservicos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.kenzowebstudio.catalogoservicos.model.Categorias;

public interface CategoriasRepository extends JpaRepository<Categorias, Long> {

Optional<Categorias> findByNomeCategoria(String nomeCategoria);
}
