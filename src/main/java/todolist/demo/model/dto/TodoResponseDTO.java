package todolist.demo.model.dto;

public record TodoResponseDTO(Long id, String titulo, boolean realizado, int prioridade, String descricao) {
}
