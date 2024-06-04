package com.riquetti.ProjetoLocadora.mapper;

import com.riquetti.ProjetoLocadora.dto.ModeloDTO;
import com.riquetti.ProjetoLocadora.entity.ModeloEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ModeloMapper {

    ModeloMapper INSTANCE = Mappers.getMapper(ModeloMapper.class);

    ModeloDTO toDTO(ModeloEntity modeloEntity);

    ModeloEntity toEntity(ModeloDTO modeloDTO);
}
