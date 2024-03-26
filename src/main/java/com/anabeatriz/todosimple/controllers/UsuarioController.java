package com.anabeatriz.todosimple.controllers;

import com.anabeatriz.todosimple.models.Tarefas;
import com.anabeatriz.todosimple.models.Usuario;
import com.anabeatriz.todosimple.services.UsuarioService;
import jakarta.servlet.Servlet;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/usuario")
@Validated
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    //localhost:8080/usuario
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Long id) {
        Usuario usuario = this.usuarioService.findById(id);
        return ResponseEntity.ok().body(usuario);
    }
    @PostMapping
    @Validated(Usuario.CreateUsuario.class)
    public ResponseEntity<Void> create(@Valid @RequestBody Usuario usuario){
        this.usuarioService.create(usuario);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @Validated(Usuario.UpdateUsuario.class)
    public ResponseEntity<Void> update(@Valid @RequestBody Usuario usuario, @PathVariable Long id){
        usuario.setId(id);
        usuario = this.usuarioService.update(usuario);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
