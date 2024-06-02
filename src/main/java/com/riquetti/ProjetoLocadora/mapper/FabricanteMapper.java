package com.riquetti.ProjetoLocadora.mapper;


import com.riquetti.ProjetoLocadora.dto.FabricanteDTO;
import com.riquetti.ProjetoLocadora.entity.FabricanteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")

public interface FabricanteMapper {
    FabricanteMapper INSTANCE = Mappers.getMapper(FabricanteMapper.class);

    FabricanteDTO paraDTO(FabricanteEntity fabricante);

    FabricanteEntity paraEntity(FabricanteDTO fabricanteDTO);

    List<FabricanteDTO> paraDTO(List<FabricanteEntity> fabricantes);

    List<FabricanteEntity> paraEntity(List<FabricanteDTO> fabricanteDTO);
}
