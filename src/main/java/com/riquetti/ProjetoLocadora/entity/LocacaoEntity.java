package com.riquetti.ProjetoLocadora.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("locacao")
public class LocacaoEntity {
    @Id
    private Long idLocacao;
    private LocalDate dataLocacao;
    private LocalDate dataDevolucao;
    private LocalDate dataDevolvida;
    private BigDecimal valor;
    private BigDecimal valorDesconto;
    private BigDecimal valorTotal;
    private Long idCarro;
    private Long idSeguradora;
    private Long idCliente;
    private String placaCarro;
    private String cnpjSeguradora;
    private String cpfCliente;
}
