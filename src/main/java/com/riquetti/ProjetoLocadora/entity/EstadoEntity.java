package com.riquetti.ProjetoLocadora.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("estado")
public class EstadoEntity {

    @Id
    private Long id_estado;
    private String descricao;
    private String sigla;
}
