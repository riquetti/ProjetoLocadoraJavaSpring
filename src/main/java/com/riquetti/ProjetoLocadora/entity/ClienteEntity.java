package com.riquetti.ProjetoLocadora.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("cliente")
public class ClienteEntity {
    @Id
    private Long id;
    private String nomeCliente;
    private String rg;
    private String cpf;
    private String logradouro;
    private Long idMunicipio;
    private Long idEstado;
    private String cnh;
    private LocalDate cnhdataVencimento;
    private String emailCliente;
    private String municipio;
    private String estado;
}
