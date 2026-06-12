package br.com.kenzowebstudio.catalogoservicos.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.kenzowebstudio.catalogoservicos.model.Servicos;

public interface ServicosRepository extends JpaRepository<Servicos, Long> {
    Optional<Servicos> findByUsuariosId(Long usuariosId);

    List<Servicos> findByCategoriaContainingIgnoreCaseAndBairroContainingIgnoreCase(String categoria, String bairro);
}