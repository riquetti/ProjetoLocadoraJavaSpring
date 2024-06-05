package com.riquetti.ProjetoLocadora.dto;

import org.springframework.data.relational.core.mapping.Column;

public record MunicipioDTO(
        Long idMunicipio,
        String descricao,
        Long idEstado,
        String estado
) {
}
