package com.riquetti.ProjetoLocadora.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("modelo")
public class ModeloEntity {
    @Id
    private Long id;
    private Long idFabricante;
    private String nomeFabricante;
    private String nome;

}
