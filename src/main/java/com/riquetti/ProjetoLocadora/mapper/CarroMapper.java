package com.riquetti.ProjetoLocadora.mapper;

import com.riquetti.ProjetoLocadora.dto.CarroDTO;
import com.riquetti.ProjetoLocadora.entity.CarroEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CarroMapper {

    CarroMapper INSTANCE = Mappers.getMapper(CarroMapper.class);

    CarroDTO toDTO(CarroEntity carroEntity);

    CarroEntity toEntity(CarroDTO carroDTO);
}
