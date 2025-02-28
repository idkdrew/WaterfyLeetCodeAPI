package com.alves.waterfy.controller;

import com.alves.waterfy.model.Tarefa;
import com.alves.waterfy.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarefa")
public class TarefaController {
    @Autowired
    private TarefaService tarefaService;

    @GetMapping("{id}")
    public ResponseEntity<Tarefa> findById(@PathVariable int id) {
        return ResponseEntity.ok(tarefaService.findById(id));
    }

    @GetMapping()
    public ResponseEntity<List<Tarefa>> findAll(){
        return ResponseEntity.ok(tarefaService.findAll());
    }


    @PostMapping()
    public ResponseEntity<Object> save(@RequestBody Tarefa tarefa) {
        Tarefa novaTarefa = tarefaService.save(tarefa);
        return ResponseEntity.ok(novaTarefa);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> update(@PathVariable int id, @RequestBody Tarefa tarefa) {
        Tarefa updatedTarefa = tarefaService.update(id, tarefa);
        return ResponseEntity.ok(updatedTarefa);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        tarefaService.delete(id);
    }

}
