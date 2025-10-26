package todolist.demo.model.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TodoRequestDTO(
        @NotBlank @NotNull String titulo,
        boolean realizado,
        int prioridade,
        @NotBlank @NotNull String descricao
){
}
