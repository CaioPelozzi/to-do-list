package todolist.demo.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;


import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import todolist.demo.model.Todo;
import todolist.demo.servicies.todoService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class todoController {

    private final todoService todoService;

    public todoController(todoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public ResponseEntity<List<Todo>> list(){
        return ResponseEntity.ok().body(todoService.list());
    }

    @PostMapping
    public ResponseEntity<List<Todo>> save(@RequestBody Todo todo){
        return ResponseEntity.status(HttpStatus.OK).body(todoService.save(todo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        todoService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Excluído com sucesso!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<List<Todo>> update(@PathVariable Long id,@RequestBody Todo todo){
        todoService.update(id, todo);
        return ResponseEntity.status(HttpStatus.OK).body(todoService.list());
    }
    
}
