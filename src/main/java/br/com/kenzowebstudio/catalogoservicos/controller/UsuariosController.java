package br.com.kenzowebstudio.catalogoservicos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.kenzowebstudio.catalogoservicos.dto.UsuarioDTO;
import br.com.kenzowebstudio.catalogoservicos.dto.UsuarioCadastroDTO;
import br.com.kenzowebstudio.catalogoservicos.model.Usuarios;
import br.com.kenzowebstudio.catalogoservicos.service.UsuariosService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class UsuariosController {

    @Autowired
    private UsuariosService usuariosService;

    //endpoint post api/auth/registrar usando UsuarioCadastroDTO
    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@RequestBody UsuarioCadastroDTO dto){
        try {
            Usuarios novoUsuarios = new Usuarios();
            novoUsuarios.setNome(dto.getNome());
            novoUsuarios.setEmail(dto.getEmail());
            novoUsuarios.setSenha(dto.getSenha()); // senha pura vai ser criptografada no service
            novoUsuarios.setTipoPerfil(dto.getTipoPerfil());

            Usuarios salvo = usuariosService.cadastrarUsuarios(novoUsuarios);

            UsuarioDTO resposta = new UsuarioDTO(
                salvo.getId(),
                salvo.getNome(),
                salvo.getEmail(),
                salvo.getTipoPerfil()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(resposta);

        } catch(RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
