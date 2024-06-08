package com.riquetti.ProjetoLocadora.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("seguradora")
public class SeguradoraEntity {
    @Id
    private Long idSeguradora;
    private String nome;
    private String cnpj;
    private String email;
    private Long valor;
    private Long idMunicipio;
    private Long idEstado;
    private String municipio;
    private String estado;
}
