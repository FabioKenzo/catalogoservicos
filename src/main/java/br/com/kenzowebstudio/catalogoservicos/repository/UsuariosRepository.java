package br.com.kenzowebstudio.catalogoservicos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.kenzowebstudio.catalogoservicos.model.Usuarios;

public interface UsuariosRepository extends JpaRepository<Usuarios, Long> {

    Optional<Usuarios> findByEmail(String email);

}
