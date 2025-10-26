package todolist.demo.servicies;

import java.util.List;
import java.util.stream.Collectors;


import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import todolist.demo.model.Todo;
import todolist.demo.model.dto.TodoRequestDTO;
import todolist.demo.model.dto.TodoResponseDTO;
import todolist.demo.repositories.todoRepository;

@Service
public class todoService {

    private final todoRepository todoRepository;

    public todoService(todoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<TodoResponseDTO> list(){
        Sort sort = Sort.by("prioridade").ascending();
        List<Todo> todo = todoRepository.findAll(sort);
        return todo.stream()
        .map(t -> new TodoResponseDTO(t.getId(), t.getTitulo(), t.isRealizado(), t.getPrioridade(), t.getDescricao()))
                .collect(Collectors.toList());
    }

    public List<TodoResponseDTO> save(TodoRequestDTO dto) {
        Todo todo = new Todo();
        todo.setTitulo(dto.titulo());
        todo.setDescricao(dto.descricao());
        todo.setPrioridade(dto.prioridade());
        todo.setRealizado(dto.realizado());
        todoRepository.save(todo);
        return list();
    }

    public List<TodoResponseDTO> delete(Long id) {
        todoRepository.deleteById(id);
        return list();
    }

    public List<TodoResponseDTO> update(Long id, TodoRequestDTO dto){
        Todo todoExistente = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo não encontrado com id: " + id));
        todoExistente.setTitulo(dto.titulo());
        todoExistente.setDescricao(dto.descricao());
        todoExistente.setPrioridade(dto.prioridade());
        todoExistente.setRealizado(dto.realizado());
        todoRepository.save(todoExistente);
        return list();
    }
}
