package com.anabeatriz.todosimple.services;

import com.anabeatriz.todosimple.models.TarefasAvaliativas;
import com.anabeatriz.todosimple.models.Usuario;
import com.anabeatriz.todosimple.repositories.TarefasAvaliativasRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TarefasAvaliativasService implements TarefasService<TarefasAvaliativas>{

    @Autowired
    private TarefasAvaliativasRepository tarefasAvaliativasRepository;

    @Autowired
    private UsuarioService usuarioService;

    public TarefasAvaliativas findById(Long id){
        Optional<TarefasAvaliativas> tarefasAvaliativas = this.tarefasAvaliativasRepository.findById(id);
        return tarefasAvaliativas.orElseThrow(() -> new RuntimeException (
                "Tarefa avaliativa não encontrada! id: " + id + ", Tipo: " + TarefasAvaliativas.class.getName()));
    }

    public List<TarefasAvaliativas> findAllByUserId(Long usuarioId) {
        List<TarefasAvaliativas> tarefasAvaliativas = this.tarefasAvaliativasRepository.findByUsuario_Id(usuarioId);
        return tarefasAvaliativas;
    }

    public List<TarefasAvaliativas> findAllByNota(double nota){
        return tarefasAvaliativasRepository.findByNota(nota);
    }

    public List<TarefasAvaliativas> findAllByDataAvaliacao(Date dataAvaliacao) {
        return tarefasAvaliativasRepository.findByDataAvaliacao(dataAvaliacao);
    }

    @Transactional
    public TarefasAvaliativas create(TarefasAvaliativas obj) {
        Usuario usuario = this.usuarioService.findById(obj.getUsuario().getId());
        obj.setId(null);
        obj.setUsuario(usuario);

        obj = this.tarefasAvaliativasRepository.save(obj);
        return obj;
    }

    @Transactional
    public TarefasAvaliativas update(TarefasAvaliativas obj){
        TarefasAvaliativas newObj = findById(obj.getId());
        newObj.setDescricao(obj.getDescricao());
        return this.tarefasAvaliativasRepository.save(newObj);
    }

    public void delete(Long id){
        findById(id);
        try {
            this.tarefasAvaliativasRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir pois não há entidades relacionadas!");
        }
    }
}
