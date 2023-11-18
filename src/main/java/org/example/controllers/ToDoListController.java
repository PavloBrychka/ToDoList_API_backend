package org.example.controllers;

import lombok.AllArgsConstructor;
import org.example.dto.ToDoItemDTO;
import org.example.entities.ToDoListEntity;
import org.example.mapper.ToDoMapper;
import org.example.repositories.ToDoListRepository;
import org.example.service.ToDoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
@RequestMapping("/todo")
public class ToDoListController {

    private final ToDoMapper toDoMapper;
    private final ToDoListRepository toDoListRepository;
    private final ToDoListService toDoListService;

    @GetMapping("/all")
    public ResponseEntity<List<ToDoItemDTO>> getAllTasks() {
        var result = toDoMapper.listToDoItemDTO(toDoListRepository.findAll());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ToDoItemDTO> getTaskById(@PathVariable int id) {
        Optional<ToDoListEntity> taskOptional = toDoListRepository.findById(id);

        if (taskOptional.isPresent()) {
            ToDoListEntity task = taskOptional.get();
            ToDoItemDTO taskDTO = toDoMapper.toDoItemDTO(task);
            return new ResponseEntity<>(taskDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ToDoItemDTO> addTask(@RequestBody ToDoListEntity task) {
        try {
            ToDoListEntity addedTask = toDoListService.addTask(task);
            ToDoItemDTO addedTaskDTO = toDoMapper.toDoItemDTO(addedTask);
            return new ResponseEntity<>(addedTaskDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();  // Виведіть інформацію про помилку в консоль або логи
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ToDoItemDTO> updateTask(@PathVariable int id, @RequestBody ToDoListEntity task) {
        ToDoListEntity updatedTask = toDoListService.updateTask(id, task);

        if (updatedTask != null) {
            ToDoItemDTO updatedTaskDTO = toDoMapper.toDoItemDTO(updatedTask);
            return new ResponseEntity<>(updatedTaskDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable int id) {
        try {
            toDoListService.deleteTask(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}