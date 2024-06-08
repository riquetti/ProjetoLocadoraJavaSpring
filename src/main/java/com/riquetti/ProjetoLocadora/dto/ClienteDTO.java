package com.riquetti.ProjetoLocadora.dto;

import java.time.LocalDate;

public record ClienteDTO(

        Long id,
        String nomeCliente,
        String rg,
        String cpf,
        String logradouro,
        Long idMunicipio,
        Long idEstado,
        String cnh,
        LocalDate cnhdataVencimento,
        String emailCliente,
        String municipio,
        String estado

) {
}
