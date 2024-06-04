package com.riquetti.ProjetoLocadora.dto;

public record CarroDTO(
        Long id,
        String placa,
        String cor,
        boolean disponivel,
        Long ano,
        Long valorLocacao,
        Long idFabricante,
        Long idModelo,
        String modelo,
        String fabricante
) {
}
