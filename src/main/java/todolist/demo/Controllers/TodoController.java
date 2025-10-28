package todolist.demo.Controllers;

import jakarta.validation.Valid;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;


import todolist.demo.model.dto.TodoRequestDTO;
import todolist.demo.model.dto.TodoResponseDTO;
import todolist.demo.servicies.TodoService;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<TodoResponseDTO>>> list(){
        CollectionModel<EntityModel<TodoResponseDTO>> todos = todoService.list();
        return ResponseEntity.status(HttpStatus.OK).body(todoService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<TodoResponseDTO>> getTodoById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(todoService.getTodoById(id));
    }

    @PostMapping
    public ResponseEntity<CollectionModel<EntityModel<TodoResponseDTO>>> save(@RequestBody @Valid TodoRequestDTO todo){
        return ResponseEntity.status(HttpStatus.CREATED).body(todoService.save(todo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        todoService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Excluído com sucesso!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<CollectionModel<EntityModel<TodoResponseDTO>>> update(@PathVariable Long id,@RequestBody @Valid TodoRequestDTO todo){
        todoService.update(id, todo);
        return ResponseEntity.status(HttpStatus.OK).body(todoService.list());
    }
    
}
