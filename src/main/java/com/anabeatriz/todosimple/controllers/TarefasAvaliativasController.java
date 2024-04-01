package com.anabeatriz.todosimple.controllers;


import com.anabeatriz.todosimple.models.TarefasAvaliativas;
import com.anabeatriz.todosimple.services.TarefasAvaliativasService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/tarefas-avaliativas")
public class TarefasAvaliativasController {

    @Autowired
    private TarefasAvaliativasService tarefasAvaliativasService;

    @GetMapping("/{id}")
    public ResponseEntity<TarefasAvaliativas> findById(@PathVariable Long id) {
        TarefasAvaliativas tarefaAvaliativa = tarefasAvaliativasService.findById(id);
        return ResponseEntity.ok(tarefaAvaliativa);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<TarefasAvaliativas>> findAllByUserId(@PathVariable Long usuarioId) {
        List<TarefasAvaliativas> tarefasAvaliativas = tarefasAvaliativasService.findAllByUserId(usuarioId);
        return ResponseEntity.ok(tarefasAvaliativas);
    }

    @GetMapping("/nota/{nota}")
    public List<TarefasAvaliativas> findAllByNota(@PathVariable double nota) {
        return tarefasAvaliativasService.findAllByNota(nota);
    }

    @GetMapping("/data-avaliacao/{data}")
    public List<TarefasAvaliativas> findAllByDataAvaliacao(@PathVariable Date data) {
        return tarefasAvaliativasService.findAllByDataAvaliacao(data);
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody TarefasAvaliativas tarefaAvaliativa) {
        TarefasAvaliativas createdTarefaAvaliativa = tarefasAvaliativasService.create(tarefaAvaliativa);
        URI location = URI.create("/tarefas-avaliativas/" + createdTarefaAvaliativa.getId());
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody TarefasAvaliativas tarefaAvaliativa) {
        tarefaAvaliativa.setId(id);
        tarefasAvaliativasService.update(tarefaAvaliativa);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        tarefasAvaliativasService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
