package com.exemplo.todo.service;

import com.exemplo.todo.dto.TarefaDTO;
import com.exemplo.todo.model.Tarefa;
import com.exemplo.todo.model.Usuario;
import com.exemplo.todo.repository.TarefaRepository;
import com.exemplo.todo.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final UsuarioRepository usuarioRepository;

    public Tarefa salvar(TarefaDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Tarefa tarefa = Tarefa.builder()
                .titulo(dto.getTitulo())
                .descricao(dto.getDescricao())
                .concluida(dto.getConcluida())
                .usuario(usuario)
                .build();
        return tarefaRepository.save(tarefa);
    }

    public List<Tarefa> listar() {
        return tarefaRepository.findAll();
    }

    public Tarefa concluir(Long id) {
        Tarefa tarefa = tarefaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
        tarefa.setConcluida(true);
        return tarefaRepository.save(tarefa);
    }
}
