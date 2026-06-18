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
import br.com.kenzowebstudio.catalogoservicos.model.Usuarios;
import br.com.kenzowebstudio.catalogoservicos.service.UsuariosService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*") // permite o angular consumir a api em outra porta sem erro 
public class UsuariosController {

    @Autowired
    private UsuariosService usuariosService;

    //endpoint post api/auth/registrar
    @PostMapping("/registrar")
    
    public ResponseEntity<?> registrar(@RequestBody Usuarios usuarios){
        try{

            Usuarios novoUsuarios = usuariosService.cadastrarUsuarios(usuarios);

            UsuarioDTO dto = new UsuarioDTO(
                novoUsuarios.getId(), 
                novoUsuarios.getNome(), 
                novoUsuarios.getEmail(), 
                novoUsuarios.getTipoPerfil()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(dto);

        }catch(RuntimeException e){

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //endpoint post api/auth/login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuarios loginRequest){
        try{

            Usuarios usuariosLogado = usuariosService.realizarLogin(loginRequest.getEmail(), loginRequest.getSenha());

            UsuarioDTO dto = new UsuarioDTO(
                usuariosLogado.getId(), 
                usuariosLogado.getNome(),
                usuariosLogado.getEmail(), 
                usuariosLogado.getTipoPerfil()
            );
            return ResponseEntity.ok(dto);
            

        }catch(RuntimeException e){
            //se a senha ou e-mail estiverem errados retorna erro 400
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
