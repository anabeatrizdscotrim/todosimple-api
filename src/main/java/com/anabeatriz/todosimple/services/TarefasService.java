package com.anabeatriz.todosimple.services;

import com.anabeatriz.todosimple.models.Tarefas;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TarefasService<T> {
    T findById(Long id);
    List<T> findAllByUserId(Long usuarioId);
    T create(T obj);
    T update(T obj);
    void delete(Long id);
}
