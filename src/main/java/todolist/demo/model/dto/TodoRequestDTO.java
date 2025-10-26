package todolist.demo.model.dto;

public record TodoRequestDTO(String titulo, boolean realizado, int prioridade, String descricao) {
}
