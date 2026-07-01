package br.com.kenzowebstudio.catalogoservicos.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.kenzowebstudio.catalogoservicos.model.Usuarios;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;


@Service
public class TokenService {

    //le senha defina no application properties
    @Value("${api.security.token.secret}")
    private String secret; 

    //gera chave criptografada 
    private SecretKey getSignKey(){
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    //metodo para gerar o token jwt apos login 
    public String gerarToken(Usuarios usuarios){

        try{
            SecretKey key = getSignKey();
            
            return Jwts.builder()
            .subject(usuarios.getEmail())  //indentificador unico do user
                    .claim("id", usuarios.getId())  //guarda infos extra pra payload
                    .claim("perfil", usuarios.getTipoPerfil())
                    .issuedAt(new Date())  //gera data de validade
                    .expiration(gerarDataExpiracao()) 
                    .signWith(key) //assina o token
                    .compact();


        }catch(Exception e){
            throw new RuntimeException("Erro ao gerar token jwt", e);
        }
    }

    //valida token enviado pelo front
    public String validarToken(String tokenJwt){

        try{
            SecretKey key = getSignKey(); 

            //le token valida e extrai email subject
            return Jwts.parser()
            .verifyWith(key)
            .build()
            .parseSignedClaims(tokenJwt)
            .getPayload()
            .getSubject();
        }
        catch(Exception e){
            //se der algum problema cai aqui
            return null;
        }
    }

    //defini o tempo de expiracao do token
    private Date gerarDataExpiracao(){
        return new Date(System.currentTimeMillis() + 2 * 60 * 60 * 1000);
    }

}
