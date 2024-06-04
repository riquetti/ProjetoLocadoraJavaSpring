package com.riquetti.ProjetoLocadora.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("carro")
public class CarroEntity {
    @Id
    private Long id;
    private String placa;
    private String cor;
    private boolean disponivel;
    private Long ano;
    private Long valorLocacao;
    private Long idFabricante;
    private Long idModelo;
    private String modelo;
    private String fabricante;

}
