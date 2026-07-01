package br.com.kenzowebstudio.catalogoservicos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.kenzowebstudio.catalogoservicos.model.Usuarios;
import br.com.kenzowebstudio.catalogoservicos.security.TokenService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO dados) {
        try {
            var authenticationToken = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
            var authentication = authenticationManager.authenticate(authenticationToken);
            
            var usuarioLogado = (Usuarios) authentication.getPrincipal();
            
            //força o token a ser reconhecido como String
            String tokenJWT = (String) tokenService.gerarToken(usuarioLogado);
            
            //converte os retornos para String para evitar conflito com enums
            return ResponseEntity.ok(new TokenResponseDTO(
                usuarioLogado.getId(),
                usuarioLogado.getNome(),
                String.valueOf(usuarioLogado.getTipoPerfil()), //protege contra erro se for enum
                tokenJWT
            ));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("E-mail ou senha inválidos!");
        }
    }
}

// record dto auxiliar para receber dados do angular
record LoginDTO(String email, String senha) {
}

//record aceita o id para o Angular salvar na sessao
record TokenResponseDTO(Long id, String nome, String tipoPerfil, String token) {
}
