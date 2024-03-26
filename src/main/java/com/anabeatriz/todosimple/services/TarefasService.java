package com.anabeatriz.todosimple.services;

import aj.org.objectweb.asm.Opcodes;
import com.anabeatriz.todosimple.models.Tarefas;
import com.anabeatriz.todosimple.models.Usuario;
import com.anabeatriz.todosimple.repositories.TarefasRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarefasService {

    @Autowired
    private TarefasRepository tarefasRepository;

    @Autowired
    private UsuarioService usuarioService;

    public Tarefas findById(Long id){
        Optional<Tarefas> tarefas = this.tarefasRepository.findById(id);
        return tarefas.orElseThrow(() -> new RuntimeException (
                "Tarefa não encontrada! id: " + id + ", Tipo: " + Tarefas.class.getName()));

    }

    public List<Tarefas> findAllByUserId(Long usuarioId) {
        List<Tarefas> tarefas = this.tarefasRepository.findByUsuario_Id(usuarioId);
        return tarefas;
    }

    @Transactional
    public Tarefas create(Tarefas obj) {
        Usuario usuario = this.usuarioService.findById(obj.getUsuario().getId());
        obj.setId(null);
        obj.setUsuario(usuario);

        obj = this.tarefasRepository.save(obj);
        return obj;
    }

    @Transactional
    public Tarefas update(Tarefas obj){
        Tarefas newObj = findById(obj.getId());
        newObj.setDescricao(obj.getDescricao());
        return this.tarefasRepository.save(newObj);
    }

    public void delete(Long id){
        findById(id);
        try {
            this.tarefasRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir pois não há entidades relacionadas!");
        }
    }
}
