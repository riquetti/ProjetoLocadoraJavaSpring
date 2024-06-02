package com.riquetti.ProjetoLocadora.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FabricanteDTO {

    @Id
    private Long id;

    private String nome;

}
