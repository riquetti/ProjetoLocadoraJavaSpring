package com.riquetti.ProjetoLocadora.dto;

public record SeguradoraDTO(

        Long idSeguradora,
        String nome,
        String cnpj,
        String email,
        Long valor,
        Long idMunicipio,
        Long idEstado,
        String municipio,
        String estado

) {
}
