package com.riquetti.ProjetoLocadora.dto;

public record ClienteTelefoneDTO(

        Long idTelefone,
        Long idCliente,
        String telefone,
        String cpfCliente
) {
}
