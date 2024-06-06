package com.riquetti.ProjetoLocadora.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("clientetelefone")
public class ClienteTelefoneEntity {

    @Id
    private Long idTelefone;
    private Long idCliente;
    private String telefone;
    private String cpfCliente;
}
