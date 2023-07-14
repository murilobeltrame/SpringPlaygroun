package br.com.murilobeltrame.springplayground.controllers;

import br.com.murilobeltrame.springplayground.models.Todo;
import br.com.murilobeltrame.springplayground.repositories.TodosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todos")
@Validated
public class TodosController {
    @Autowired
    TodosRepository repository;

    @GetMapping
    public ResponseEntity<List<Todo>> getAll() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Todo> create(@Valid @RequestBody TodoCreateRequest request) {
        try {
            Todo createdTodo = repository.save(Todo.from(request));
            return new ResponseEntity<>(createdTodo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Todo> getById(@PathVariable("id") @Min(1) long id) {
        Optional<Todo> todo = repository.findById(id);
        return todo
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable("id") @Min(1) long id,
            @Valid @RequestBody TodoUpdateRequest request) {
        try {
            Optional<Todo> foundTodo = repository.findById(id);
            if (foundTodo.isPresent()) {
                Todo updatedTodo = foundTodo.get().with(request);
                repository.save(updatedTodo);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") @Min(1) long id) {
        try {
            Optional<Todo> todo = repository.findById(id);
            if (!todo.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
