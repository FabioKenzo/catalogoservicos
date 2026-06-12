package br.com.kenzowebstudio.catalogoservicos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

            Usuarios novUsuarios = usuariosService.cadastrarUsuarios(usuarios);
            return ResponseEntity.status(HttpStatus.CREATED).body(novUsuarios);

        }catch(RuntimeException e){

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //endpoint post api/auth/login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuarios loginRequest){
        try{

            Usuarios usuariosLogado = usuariosService.realizarLogin(loginRequest.getEmail(), loginRequest.getSenha());
            return ResponseEntity.ok(usuariosLogado);

        }catch(RuntimeException e){
            //se a senha ou e-mail estiverem errados retorna erro 400
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }




}
