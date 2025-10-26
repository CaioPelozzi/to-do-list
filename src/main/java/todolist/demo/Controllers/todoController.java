package todolist.demo.Controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;


import todolist.demo.model.dto.TodoRequestDTO;
import todolist.demo.model.dto.TodoResponseDTO;
import todolist.demo.servicies.todoService;
import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class todoController {

    private final todoService todoService;

    public todoController(todoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public ResponseEntity<List<TodoResponseDTO>> list(){
        return ResponseEntity.status(HttpStatus.OK).body(todoService.list());
    }

    @PostMapping
    public ResponseEntity<List<TodoResponseDTO>> save(@RequestBody @Valid TodoRequestDTO todo){
        return ResponseEntity.status(HttpStatus.CREATED).body(todoService.save(todo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        todoService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Excluído com sucesso!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<List<TodoResponseDTO>> update(@PathVariable Long id,@RequestBody @Valid TodoRequestDTO todo){
        todoService.update(id, todo);
        return ResponseEntity.status(HttpStatus.OK).body(todoService.list());
    }
    
}
