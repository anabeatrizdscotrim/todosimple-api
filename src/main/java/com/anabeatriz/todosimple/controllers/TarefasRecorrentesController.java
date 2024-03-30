package com.anabeatriz.todosimple.controllers;

import com.anabeatriz.todosimple.models.TarefasRecorrentes;
import com.anabeatriz.todosimple.services.TarefasRecorrentesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tarefas-recorrentes")
public class TarefasRecorrentesController {

    @Autowired
    private TarefasRecorrentesService tarefasRecorrentesService;

    @GetMapping("/{id}")
    public ResponseEntity<TarefasRecorrentes> findById(@PathVariable Long id) {
        TarefasRecorrentes tarefaRecorrente = tarefasRecorrentesService.findById(id);
        return ResponseEntity.ok(tarefaRecorrente);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<TarefasRecorrentes>> findAllByUserId(@PathVariable Long usuarioId) {
        List<TarefasRecorrentes> tarefasRecorrentes = tarefasRecorrentesService.findAllByUserId(usuarioId);
        return ResponseEntity.ok(tarefasRecorrentes);
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody TarefasRecorrentes tarefaRecorrente) {
        TarefasRecorrentes createdTarefaRecorrente = tarefasRecorrentesService.create(tarefaRecorrente);
        URI location = URI.create("/tarefas-recorrentes/" + createdTarefaRecorrente.getId());
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody TarefasRecorrentes tarefaRecorrente) {
        tarefaRecorrente.setId(id);
        tarefasRecorrentesService.update(tarefaRecorrente);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        tarefasRecorrentesService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
