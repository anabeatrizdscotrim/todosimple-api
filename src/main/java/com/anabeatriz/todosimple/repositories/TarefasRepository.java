package com.anabeatriz.todosimple.repositories;

import com.anabeatriz.todosimple.models.Tarefas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarefasRepository extends JpaRepository<Tarefas, Long> {
    List<Tarefas> findByUsuario_Id(Long id);
}
