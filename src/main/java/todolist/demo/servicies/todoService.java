package todolist.demo.servicies;

import java.util.List;


import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import todolist.demo.model.Todo;
import todolist.demo.repositories.todoRepository;

@Service
public class todoService {

    private final todoRepository todoRepository;

    public todoService(todoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> list(){
        Sort sort = Sort.by("prioridade").ascending();
        return todoRepository.findAll();
    }

    public List<Todo> save(Todo todo) {
        todoRepository.save(todo);
        return list();
    }

    public List<Todo> delete(Long id) {
        todoRepository.deleteById(id);
        return list();
    }

    public List<Todo> update(Long id, Todo todo){
        Todo todoExistente = todoRepository.findById(id)
         .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
        todoExistente.setTitulo((todo.getTitulo()));
        todoExistente.setDescricao((todo.getDescricao()));
        todoExistente.setPrioridade((todo.getPrioridade()));
        todoExistente.setRealizado((todo.isRealizado()));
        todoRepository.save(todoExistente);
        return list();
    }
}
