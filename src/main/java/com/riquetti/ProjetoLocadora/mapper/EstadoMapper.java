package com.riquetti.ProjetoLocadora.mapper;

import com.riquetti.ProjetoLocadora.dto.EstadoDTO;
import com.riquetti.ProjetoLocadora.entity.EstadoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EstadoMapper {
    EstadoMapper INSTANCE = Mappers.getMapper(EstadoMapper.class);

    EstadoDTO toDTO(EstadoEntity estadoEntity);

    EstadoEntity toEntity(EstadoDTO estadoDTO);
}
