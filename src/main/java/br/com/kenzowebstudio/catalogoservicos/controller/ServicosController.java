package br.com.kenzowebstudio.catalogoservicos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.kenzowebstudio.catalogoservicos.model.Servicos;
import br.com.kenzowebstudio.catalogoservicos.service.ServicosService;
import java.util.Map;

@RestController
@RequestMapping("/api/servicos")
@CrossOrigin(origins = "*") // Permite que o Angular (porta 4200) acesse o Java (porta 8080)
public class ServicosController {

    @Autowired
    private ServicosService servicosService;

    @PostMapping("/salvar")
    public ResponseEntity<?> salvarServico(@RequestBody Map<String, Object> payload) {
        try {
            String categoria = (String) payload.get("categoria");
            String bairro = (String) payload.get("bairro");
            String telefone = (String) payload.get("telefone");
            String descricao = (String) payload.get("descricao");
            
            // Converte o ID enviado pelo Angular para Long
            Long usuarioId = Long.valueOf(payload.get("usuarioId").toString());

            Servicos servicoSalvo = servicosService.salvarOuAtualizar(categoria, bairro, telefone, descricao, usuarioId);
            return ResponseEntity.status(HttpStatus.CREATED).body(servicoSalvo);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao processar serviço: " + e.getMessage());
        }
    }

    @GetMapping("/buscar")
public ResponseEntity<java.util.List<Servicos>> buscar(
        @RequestParam(required = false, defaultValue = "") String categoria,
        @RequestParam(required = false, defaultValue = "") String bairro) {
    
    java.util.List<Servicos> resultados = servicosService.buscarServicos(categoria, bairro);
    return ResponseEntity.ok(resultados);
}
}
