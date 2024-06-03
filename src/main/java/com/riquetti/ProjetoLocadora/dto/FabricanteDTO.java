package com.riquetti.ProjetoLocadora.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


public record FabricanteDTO(
        Long id,
        String nome
) {
}
