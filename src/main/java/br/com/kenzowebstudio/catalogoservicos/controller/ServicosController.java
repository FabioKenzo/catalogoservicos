package br.com.kenzowebstudio.catalogoservicos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import br.com.kenzowebstudio.catalogoservicos.dto.ServicoDTO;
import br.com.kenzowebstudio.catalogoservicos.dto.ServicoRequestDTO;
import br.com.kenzowebstudio.catalogoservicos.model.Servicos;
import br.com.kenzowebstudio.catalogoservicos.service.ServicosService;

@RestController
@RequestMapping("/api/servicos")
@CrossOrigin(origins = "*") //permite que o angular (porta 4200) acesse o Java (porta 8080)
public class ServicosController {

    @Autowired
    private ServicosService servicosService;

    @PostMapping("/salvar")
    @PreAuthorize("hasRole('PRESTADOR')")
    public ResponseEntity<ServicoDTO> salvarServico(@RequestBody ServicoRequestDTO dto) {
        try {
            //executa a regra de negocio usando os dados validados do dto
            Servicos servicoSalvo = servicosService.salvarOuAtualizar(
                    dto.getCategoria(),
                    dto.getBairro(),
                    dto.getTelefone(),
                    dto.getDescricao(),
                    dto.getUsuarioId());

            //converte a entidade para o dto de saida
            ServicoDTO responseDTO = new ServicoDTO(servicoSalvo);

            //retorna o dto com status 201
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<java.util.List<ServicoDTO>> buscar(
            @RequestParam(required = false, defaultValue = "") String categoria,
            @RequestParam(required = false, defaultValue = "") String bairro) {

        //busca a lista de entidades normais do banco
        java.util.List<Servicos> resultados = servicosService.buscarServicos(categoria, bairro);

        //transforma cada entidade da lista em um ServicoDTO
        java.util.List<ServicoDTO> listaDto = resultados.stream()
                .map(ServicoDTO::new)
                .toList();

        return ResponseEntity.ok(listaDto);
    }
}
