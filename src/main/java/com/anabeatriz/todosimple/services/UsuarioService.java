package com.anabeatriz.todosimple.services;

import com.anabeatriz.todosimple.models.Usuario;
import com.anabeatriz.todosimple.repositories.TarefasRepository;
import com.anabeatriz.todosimple.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.aspectj.bridge.IMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.channels.ScatteringByteChannel;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TarefasRepository tarefasRepository;

    public Usuario findById(Long id) {
        Optional<Usuario> usuario = this.usuarioRepository.findById(id);
        return usuario.orElseThrow(() -> new RuntimeException (
         "Usuario não encontrado! id: " + id + ", Tipo: " + Usuario.class.getName()
        ));
    }

    @Transactional
    public Usuario create(Usuario obj) {
        obj.setId(null);
        obj = this.usuarioRepository.save(obj);
        this.tarefasRepository.saveAll(obj.getTarefas());
        return obj;
    }

    @Transactional
    public Usuario update(Usuario obj) {
        Usuario newObj = findById(obj.getId());
        newObj.setPassword(obj.getPassword());
        return this.usuarioRepository.save(newObj);
    }

    public void delete(Long id){
        findById(id);
        try {
            this.usuarioRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir pois não há tarefas relacionadas!");
        }
    }

}
