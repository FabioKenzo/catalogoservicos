package br.com.kenzowebstudio.catalogoservicos.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import br.com.kenzowebstudio.catalogoservicos.model.Comercios;
import br.com.kenzowebstudio.catalogoservicos.service.ComerciosService;
import br.com.kenzowebstudio.catalogoservicos.dto.ComercioDTO;

@RestController
@RequestMapping("/api/comercios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ComerciosController {

    @Autowired
    private ComerciosService comerciosService;

    @PostMapping("/salvar")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> salvarNovoComercio(@RequestBody Comercios comercios) {
        try {
            //repassa o objeto para o service gerenciar o user e criar/vincular a categoria
            Comercios novoComercio = comerciosService.salvaComerciosLivre(comercios);

            //transforma no dto limpo para responder ao angular sem loops ou dado confidenciais
            ComercioDTO responseDTO = new ComercioDTO(novoComercio);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    
    @GetMapping("/buscar")
    public ResponseEntity<?> buscarPorServicoEBairro(@RequestParam String servico, @RequestParam String bairro) {
        try {
            List<Comercios> resultados = comerciosService.buscarPorServicoEBairro(servico, bairro);

            //converte a lista de entidades para a lista de dtos limpos que os cards entendem
            List<ComercioDTO> listaDto = resultados.stream()
                    .map(ComercioDTO::new)
                    .toList();

            return ResponseEntity.ok(listaDto);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //busca por id
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            Comercios comercios = comerciosService.buscarPorId(id);
            ComercioDTO responseDTO = new ComercioDTO(comercios);
            return ResponseEntity.ok(responseDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
