package com.anabeatriz.todosimple.repositories;

import com.anabeatriz.todosimple.models.TarefasAvaliativas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarefasAvaliativasRepository extends JpaRepository<TarefasAvaliativas, Long> {
    List<TarefasAvaliativas>findByUsuario_Id(Long id);
}
