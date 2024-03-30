package com.anabeatriz.todosimple.services;

import com.anabeatriz.todosimple.models.TarefasRecorrentes;
import com.anabeatriz.todosimple.models.Usuario;
import com.anabeatriz.todosimple.repositories.TarefasRecorrentesRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarefasRecorrentesService implements TarefasService<TarefasRecorrentes> {

    @Autowired
    private TarefasRecorrentesRepository tarefasRecorrentesRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public TarefasRecorrentes findById(Long id){
        Optional<TarefasRecorrentes> tarefas = tarefasRecorrentesRepository.findById(id);
        return tarefas.orElseThrow(() -> new RuntimeException(
                "Tarefa recorrente não encontrada! id: " + id + ", Tipo: " + TarefasRecorrentes.class.getName()));
    }

    @Override
    public List<TarefasRecorrentes> findAllByUserId(Long usuarioId) {
        return tarefasRecorrentesRepository.findByUsuario_Id(usuarioId);
    }

    @Override
    @Transactional
    public TarefasRecorrentes create(TarefasRecorrentes obj) {
        Usuario usuario = usuarioService.findById(obj.getUsuario().getId());
        obj.setId(null);
        obj.setUsuario(usuario);
        return tarefasRecorrentesRepository.save(obj);
    }

    @Override
    @Transactional
    public TarefasRecorrentes update(TarefasRecorrentes obj) {
        TarefasRecorrentes newObj = findById(obj.getId());
        newObj.setDescricao(obj.getDescricao());
        // Defina outras atualizações necessárias aqui
        return tarefasRecorrentesRepository.save(newObj);
    }

    @Override
    public void delete(Long id) {
        findById(id);
        try {
            tarefasRecorrentesRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir pois não há entidades relacionadas!");
        }

    }
}
