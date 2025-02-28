package com.alves.waterfy.service;

import com.alves.waterfy.exception.CharacterLimitException;
import com.alves.waterfy.exception.InvalidDateFormatException;
import com.alves.waterfy.exception.TaskNotFoundException;
import com.alves.waterfy.model.Tarefa;
import com.alves.waterfy.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {
    @Autowired
    private TarefaRepository tarefaRepository;

    public Tarefa findById(Integer id) {
        return tarefaRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Tarefa não encontrada"));
    }

    public List<Tarefa> findAll(){return tarefaRepository.findAll();}

    public boolean isTitleOrDescriptionValid(Tarefa tarefa) {
        return tarefa.getTitulo().length() < 255 && tarefa.getDescricao().length() < 255;
    }

    public boolean isValidDate(Tarefa tarefa) {
        return tarefa.getDataDeVencimento() == null;
    }

    public Tarefa save(Tarefa tarefa) {
        if (!isTitleOrDescriptionValid(tarefa)) {
            throw new CharacterLimitException();
        }else if(isValidDate(tarefa)) {
            throw new InvalidDateFormatException();
        }
        return tarefaRepository.save(tarefa);
    }

    public Tarefa update(int id, Tarefa novaTarefa) {
        Tarefa tarefaExistente = findById(id);

        if(!isTitleOrDescriptionValid(novaTarefa)) {
            throw new CharacterLimitException();
        } else if (isValidDate(novaTarefa)) {
            throw new InvalidDateFormatException();
        }

        tarefaExistente.setTitulo(novaTarefa.getTitulo());
        tarefaExistente.setDescricao(novaTarefa.getDescricao());
        tarefaExistente.setStatus(novaTarefa.getStatus());
        tarefaExistente.setDataDeVencimento(novaTarefa.getDataDeVencimento());

        return tarefaRepository.save(tarefaExistente);
    }

    public void delete(int tarefa) {
        if(tarefaRepository.existsById(tarefa)) {
            tarefaRepository.deleteById(tarefa);
        } else {
            throw new TaskNotFoundException();
        }
    }
}
