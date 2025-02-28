package com.alves.waterfy.service;

import com.alves.waterfy.exception.CharacterLimitException;
import com.alves.waterfy.exception.InvalidDateFormatException;
import com.alves.waterfy.exception.TaskNotFoundException;
import com.alves.waterfy.model.Tarefa;
import com.alves.waterfy.repository.TarefaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
class TarefaServiceTest {
    @InjectMocks
    private TarefaService tarefaService;

    @Mock
    private TarefaRepository tarefaRepository;

    @Test
    public void testFindById() throws TaskNotFoundException {
        Tarefa tarefa = new Tarefa(1, "oi", "teste", "dasdasdas", LocalDate.of(2020, 1, 1));

        Mockito.when(tarefaRepository.findById(1)).thenReturn(Optional.of(tarefa));

        Tarefa resultado = tarefaService.findById(1);

        System.out.println("Resultado do testFindById: " + resultado);

        assertNotNull(resultado);
        assertEquals(tarefa, resultado);
    }

    @Test
    void testUpdateTask_TarefaNaoEncontrada() {
        Mockito.when(tarefaRepository.findById(1)).thenReturn(Optional.empty());

        Exception exception = assertThrows(TaskNotFoundException.class, () -> tarefaService.update(1, new Tarefa()));

        System.out.println("Exceção lançada: " + exception.getMessage());
    }

    @Test
    void testUpdateTask_CaracteresExcedidos() {
        Tarefa tarefaExistente = new Tarefa(1, "Título válido", "Descrição válida", "pendente", LocalDate.now());
        Mockito.when(tarefaRepository.findById(1)).thenReturn(Optional.of(tarefaExistente));

        Tarefa novaTarefa = new Tarefa(1, "Título muito grande que excede o limite permitido de caracteressssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssTítulo muito grande que excede o limite permitido de caracteressssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss",
                "Descrição válida", "pendente", LocalDate.now());

        assertFalse(tarefaService.isTitleOrDescriptionValid(novaTarefa), "A validação deveria falhar para títulos longos!");

        Exception exception = assertThrows(CharacterLimitException.class, () -> tarefaService.update(1, novaTarefa));

        System.out.println("Exceção lançada" + exception.getMessage());
    }

    @Test
    void testUpdateTask_DataInvalida() {
        Tarefa tarefaExistente = new Tarefa(1, "Título válido", "Descrição válida", "pendente", LocalDate.now());
        Mockito.when(tarefaRepository.findById(1)).thenReturn(Optional.of(tarefaExistente));

        Tarefa novaTarefa = new Tarefa(1, "Título válido", "Descrição válida", "pendente", null);

        Exception exception = assertThrows(InvalidDateFormatException.class, () -> tarefaService.update(1, novaTarefa));

        System.out.println("Exceção lançada em testUpdateTask_DataInvalida: " + exception.getMessage());
    }

    @Test
    void testSave_CaracteresExcedidos() {
        Tarefa tarefa = new Tarefa(1, "Título muito grande que excede o limite permitido de caracteressssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssTítulo muito grande que excede o limite permitido de caracteressssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss",
                "Descrição válida", "pendente", LocalDate.now());

        Exception exception = assertThrows(CharacterLimitException.class, () -> tarefaService.save(tarefa));

        System.out.println("Exceção lançada: " + exception.getClass().getSimpleName());
    }

    @Test
    void testSave_DataInvalida() {
        Tarefa tarefa = new Tarefa(1, "Título válido", "Descrição válida", "pendente", null);

        Exception exception = assertThrows(InvalidDateFormatException.class, () -> tarefaService.save(tarefa));
        System.out.println("Exceção lançada: " + exception.getClass().getSimpleName());
    }

    @Test
    void testSave_TarefaValida() {
        Tarefa tarefa = new Tarefa(1, "Título válido", "Descrição válida", "pendente", LocalDate.now());

        Mockito.when(tarefaRepository.save(any(Tarefa.class))).thenReturn(tarefa);

        Tarefa resultado = tarefaService.save(tarefa);

        assertNotNull(resultado);
        assertEquals(tarefa, resultado);
        System.out.println("Tarefa salva com sucesso: " + resultado);
    }

    @Test
    void testDelete_TarefaExiste() {
        int id = 1;

        Mockito.when(tarefaRepository.existsById(id)).thenReturn(true);

        assertDoesNotThrow(() -> tarefaService.delete(id));

        Mockito.verify(tarefaRepository, Mockito.times(1)).deleteById(id);

        System.out.println("Tarefa com ID " + id + " deletada com sucesso.");
    }

    @Test
    void testDelete_TarefaNaoExiste() {
        int id = 1;

        Mockito.when(tarefaRepository.existsById(id)).thenReturn(false);

        Exception exception = assertThrows(TaskNotFoundException.class, () -> tarefaService.delete(id));

        Mockito.verify(tarefaRepository, Mockito.never()).deleteById(id);

        System.out.println("Erro ao deletar tarefa com ID " + id + ": " + exception.getMessage());

    }

    @Test
    void testFindAll() {
        List<Tarefa> tarefas = Arrays.asList(
                new Tarefa(1, "Tarefa 1", "Descrição 1", "pendente", LocalDate.of(2024, 2, 20)),
                new Tarefa(2, "Tarefa 2", "Descrição 2", "concluída", LocalDate.of(2024, 2, 21))
        );

        Mockito.when(tarefaRepository.findAll()).thenReturn(tarefas);

        List<Tarefa> resultado = tarefaService.findAll();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Tarefa 1", resultado.get(0).getTitulo());
        assertEquals("Tarefa 2", resultado.get(1).getTitulo());

        System.out.println("Lista de tarefas retornadas:");
        resultado.forEach(tarefa -> System.out.println(tarefa.getId() + " - " + tarefa.getTitulo()));
    }

}
