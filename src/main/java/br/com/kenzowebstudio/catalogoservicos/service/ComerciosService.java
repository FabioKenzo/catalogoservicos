package br.com.kenzowebstudio.catalogoservicos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kenzowebstudio.catalogoservicos.model.Categorias;
import br.com.kenzowebstudio.catalogoservicos.model.Comercios;
import br.com.kenzowebstudio.catalogoservicos.model.Usuarios;
import br.com.kenzowebstudio.catalogoservicos.repository.ComerciosRepository;

@Service
public class ComerciosService {

    @Autowired
    private ComerciosRepository comerciosRepository;

    @Autowired
    private UsuariosService usuariosService;

    @Autowired
    private CategoriasService categoriasService;

    // criar ou atualizar o perfil do comercio
    public Comercios salvaComercios(Comercios comercios, Long usuariosId, Long categoriaId) {

        Usuarios usuarios = usuariosService.buscarPorId(usuariosId);
        if (!"PRESTADOR".equals(usuarios.getTipoPerfil())) {
            throw new RuntimeException("Apenas usuários do tipo PRESTADOR podem criar um comércio!");
        }

        // valida se a categoria informada existe no sistema
        Categorias categorias = categoriasService.buscarPorId(categoriaId);

        if (comercios.getId() == null) {
            boolean jaPossuiComercio = comerciosRepository.findAll().stream()
                    .anyMatch(c -> c.getUsuarios().getId().equals(usuariosId));

            if (jaPossuiComercio) {
                throw new RuntimeException("Este prestador já possui um comércio cadastrado!");
            }
        }
        // linka os objetos validados ao comercio antes de salvar
        comercios.setUsuarios(usuarios);
        comercios.setCategorias(categorias);

        return comerciosRepository.save(comercios);
    }

    // filtro de Busca do Consumidor
    public List<Comercios> buscarPorServicoEBairro(String nomeCategoria, String bairro) {

        // se ambos os filtros vierem vazios traz os 6 destaques mais recentes
        if ((nomeCategoria == null || nomeCategoria.trim().isEmpty()) &&
                (bairro == null || bairro.trim().isEmpty())) {

            // busca do banco do mais recente
            return comerciosRepository.findAllByOrderByIdDesc().stream()
                    .limit(6)
                    .toList();
        }

        if (nomeCategoria == null || nomeCategoria.trim().isEmpty() || bairro == null || bairro.trim().isEmpty()) {
            throw new RuntimeException("Os filtros de serviço e bairro são obrigatórios para realizar a busca!");
        }

        return comerciosRepository.findByCategoriasNomeCategoriaAndBairro(nomeCategoria, bairro);
    }

    public Comercios buscarPorId(Long id) {
        return comerciosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comércio não encontrado com o ID:" + id));
    }
}
