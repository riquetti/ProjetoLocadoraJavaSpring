package com.riquetti.ProjetoLocadora.mapper;

import com.riquetti.ProjetoLocadora.dto.FabricanteDTO;
import com.riquetti.ProjetoLocadora.entity.FabricanteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FabricanteMapper {

    FabricanteMapper INSTANCE = Mappers.getMapper(FabricanteMapper.class);

    FabricanteDTO toDTO(FabricanteEntity fabricanteEntity);

    FabricanteEntity toEntity(FabricanteDTO fabricanteDTO);

}
