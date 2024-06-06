package com.riquetti.ProjetoLocadora.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("seguradoratelefone")
public class SeguradoraTelefoneEntity {
    @Id
    private Long idSeguradoraTelefone;
    private Long idSeguradora;
    private String telefoneSeguradora;
    private String nomeSeguradora;
}
