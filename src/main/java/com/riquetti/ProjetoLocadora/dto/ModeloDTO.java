package com.riquetti.ProjetoLocadora.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


public record ModeloDTO(
        Long id,
        Long idFabricante,
        String nomeFabricante,
        String nome
) {
}
