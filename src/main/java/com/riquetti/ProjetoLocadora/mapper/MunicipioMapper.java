package com.riquetti.ProjetoLocadora.mapper;

import com.riquetti.ProjetoLocadora.dto.MunicipioDTO;
import com.riquetti.ProjetoLocadora.entity.MunicipioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MunicipioMapper {
    MunicipioMapper INSTANCE = Mappers.getMapper(MunicipioMapper.class);

    MunicipioDTO toDTO(MunicipioEntity municipioEntity);

    MunicipioEntity toEntity(MunicipioDTO municipioDTO);
}
