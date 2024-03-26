package com.anabeatriz.todosimple.controllers;


import com.anabeatriz.todosimple.models.Tarefas;
import com.anabeatriz.todosimple.services.TarefasService;
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
@RequestMapping("/tarefas")
@Validated
public class TarefasController {

    @Autowired
    private TarefasService tarefasService;

    @GetMapping("/{id}")
    public ResponseEntity<Tarefas> findById(@PathVariable Long id) {
        Tarefas tarefas = this.tarefasService.findById(id);
        return ResponseEntity.ok(tarefas);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Tarefas>> findAllByUserId(@PathVariable Long usuarioId){
        List<Tarefas> tarefas = this.tarefasService.findAllByUserId(usuarioId);
        return ResponseEntity.ok().body(tarefas);
    }

    @PostMapping
    @Validated
    public ResponseEntity<Void> create(@Valid @RequestBody Tarefas tarefas) {
        this.tarefasService.create(tarefas);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(tarefas.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @Validated
    public ResponseEntity<Void> update(@Valid @RequestBody Tarefas tarefas, @PathVariable Long id) {
       tarefas.setId(id);
       this.tarefasService.update(tarefas);
       return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.tarefasService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
}
