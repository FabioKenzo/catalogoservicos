package br.com.kenzowebstudio.catalogoservicos.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.kenzowebstudio.catalogoservicos.model.Usuarios;
import br.com.kenzowebstudio.catalogoservicos.repository.UsuariosRepository;

@Service
public class UsuariosService implements UserDetailsService {

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public Usuarios cadastrarUsuarios(Usuarios usuarios){
        Optional<Usuarios> usuariosExistence = usuariosRepository.findByEmail(usuarios.getEmail());
        if(usuariosExistence.isPresent()){
            throw new RuntimeException("Este e-mail já esta cadastrado no sistema!");
        }

        //pega a senha em texto puro
        String senhaPura = usuarios.getSenha();

        //transforma a senha em um hash seguro
        String senhaCriptografada = passwordEncoder.encode(senhaPura);

        //substitui a senha original pela criptografada antes de salvar no banco
        usuarios.setSenha(senhaCriptografada);

        return usuariosRepository.save(usuarios);
    }

    //buscar usuario por id
    public Usuarios buscarPorId(Long id){
        return usuariosRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + id));
    }

    public Usuarios realizarLogin(String email, String senhaPura){
        Usuarios usuarios = usuariosRepository.findByEmail(email)
        .orElseThrow(() -> new RuntimeException("E-mail ou senha inválidos."));

        //compara se o resultado bate com o hash armazenado
        if(!passwordEncoder.matches(senhaPura, usuarios.getSenha())){
            throw new RuntimeException("E-mail ou senha inválidos.");
        }

        return usuarios; //login efetuado com sucesso
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        return (UserDetails) usuariosRepository.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o e-mail: " + email));
    }

}
