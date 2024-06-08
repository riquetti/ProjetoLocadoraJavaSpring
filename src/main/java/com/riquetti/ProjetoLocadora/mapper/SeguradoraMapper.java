package com.riquetti.ProjetoLocadora.mapper;

import com.riquetti.ProjetoLocadora.dto.SeguradoraDTO;
import com.riquetti.ProjetoLocadora.entity.SeguradoraEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SeguradoraMapper {
    SeguradoraMapper INSTANCE = Mappers.getMapper(SeguradoraMapper.class);

    SeguradoraDTO toDTO(SeguradoraEntity seguradoraEntity);

    SeguradoraEntity toEntity(SeguradoraDTO seguradoraDTO);
}
