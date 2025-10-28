package todolist.demo.servicies;

import java.util.List;
import java.util.stream.Collectors;


import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import todolist.demo.Controllers.TodoController;
import todolist.demo.model.Todo;
import todolist.demo.model.dto.TodoRequestDTO;
import todolist.demo.model.dto.TodoResponseDTO;
import todolist.demo.repositories.TodoRepository;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public CollectionModel<EntityModel<TodoResponseDTO>> list(){
        Sort sort = Sort.by("prioridade").ascending();
        List<Todo> todo = todoRepository.findAll(sort);


        List<EntityModel<TodoResponseDTO>> todoModels = todo.stream()
                .map(t -> { // ← abre o bloco do map()
                    var dto = new TodoResponseDTO(
                            t.getId(),
                            t.getTitulo(),
                            t.isRealizado(),
                            t.getPrioridade(),
                            t.getDescricao()
                    );

                    // retorna o EntityModel dentro do map()
                    return EntityModel.of(dto,
                            linkTo(methodOn(TodoController.class).getTodoById(t.getId())).withSelfRel()
                    );
                }) // ← aqui fecha o bloco do map()
                .toList();

        return CollectionModel.of(todoModels,
                linkTo(methodOn(TodoController.class).list()).withSelfRel()
        );

    }

    public EntityModel<TodoResponseDTO> getTodoById(Long id){
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo não encontrado com id: " + id));
        var dto = new TodoResponseDTO(todo.getId(), todo.getTitulo(), todo.isRealizado(), todo.getPrioridade(), todo.getDescricao());

        EntityModel<TodoResponseDTO> model = EntityModel.of(dto);
        model.add(linkTo(methodOn(TodoController.class).list()).withSelfRel());
        return model;
    }

    public CollectionModel<EntityModel<TodoResponseDTO>> save(TodoRequestDTO dto) {
        Todo todo = new Todo();
        todo.setTitulo(dto.titulo());
        todo.setDescricao(dto.descricao());
        todo.setPrioridade(dto.prioridade());
        todo.setRealizado(dto.realizado());
        todoRepository.save(todo);
        return list();
    }

    public CollectionModel<EntityModel<TodoResponseDTO>> delete(Long id) {
        todoRepository.deleteById(id);
        return list();
    }

    public CollectionModel<EntityModel<TodoResponseDTO>> update(Long id, TodoRequestDTO dto){
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
