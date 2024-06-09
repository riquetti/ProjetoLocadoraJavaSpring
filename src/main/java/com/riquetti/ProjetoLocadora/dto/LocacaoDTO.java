package com.riquetti.ProjetoLocadora.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record LocacaoDTO(

        Long idLocacao,
        LocalDate dataLocacao,
        LocalDate dataDevolucao,
        LocalDate dataDevolvida,
        BigDecimal valor,
        BigDecimal valorDesconto,
        BigDecimal valorTotal,
        Long idCarro,
        Long idSeguradora,
        Long idCliente,
        String placaCarro,
        String cnpjSeguradora,
        String cpfCliente

) {
}
