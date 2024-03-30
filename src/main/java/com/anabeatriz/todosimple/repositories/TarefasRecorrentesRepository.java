package com.anabeatriz.todosimple.repositories;

import com.anabeatriz.todosimple.models.TarefasRecorrentes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarefasRecorrentesRepository extends JpaRepository<TarefasRecorrentes, Long> {
    List<TarefasRecorrentes> findByUsuario_Id(Long id);
}
