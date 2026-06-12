package br.com.kenzowebstudio.catalogoservicos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.kenzowebstudio.catalogoservicos.model.Comercios;
import br.com.kenzowebstudio.catalogoservicos.service.ComerciosService;

@RestController
@RequestMapping("/api/comercios")
@CrossOrigin(origins = "*")
public class ComerciosController {

    @Autowired
    private ComerciosService comerciosService;

    //endpoint post /api/comercios/usuario/{usuarioId}/categoria/{categoriaId}
    //cria ou atualiza a vitrine amarrando as chaves estrangeiras validadas pelo Service
    @PostMapping("/usuarios/{usuarioId}/categorias/{categoriaId}")
    public ResponseEntity<?> salvar(@RequestBody Comercios comercios, @PathVariable("usuarioId") Long usuarioId, @PathVariable("categoriaId") Long categoriaId) {
        try {    
            Comercios novoComercios = comerciosService.salvaComercios(comercios, usuarioId, categoriaId);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoComercios);
        }catch(RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //endpoint get /api/comercios/buscar?servico=Eletricista&bairro=Centro
    //Rota que o Angular vai consumir dinamicamente na barra de pesquisa do cliente
    @GetMapping("/buscar")
    public ResponseEntity<?> buscarPorServicoEBairro(@RequestParam String servico, @RequestParam String bairro){
        try{

            List<Comercios> resultados = comerciosService.buscarPorServicoEBairro(servico, bairro);
            return ResponseEntity.ok(resultados);

        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

    // endpoint get /api/comercios/{id}
    // caso precise carregar uma vitrine específica no front
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        try{

            Comercios comercios = comerciosService.buscarPorId(id);
            return ResponseEntity.ok(comercios);

        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }


}
