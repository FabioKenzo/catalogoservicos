package br.com.kenzowebstudio.catalogoservicos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.kenzowebstudio.catalogoservicos.model.Categorias;
import br.com.kenzowebstudio.catalogoservicos.service.CategoriasService;

@RestController
@RequestMapping("/api/categorias")
@CrossOrigin(origins = "*")
public class CategoriasController {

    @Autowired
    private CategoriasService categoriasService;

    //endpoint get /api/categorias
    //o Angular vai consumir aqui para preencher o campo de busca
    @GetMapping
    public ResponseEntity<List<Categorias>> listarTodas(){
        List<Categorias> categorias = categoriasService.listarTodas();
        return ResponseEntity.ok(categorias);
    } 

    //endpoint post /api/categorias
    @PostMapping
    public ResponseEntity<Categorias> criar(@RequestBody Categorias categorias){
        Categorias novCategorias = categoriasService.salvar(categorias);
        return ResponseEntity.status(HttpStatus.CREATED).body(novCategorias);
    }

}
