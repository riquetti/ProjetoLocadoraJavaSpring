package com.riquetti.ProjetoLocadora.mapper;

import com.riquetti.ProjetoLocadora.dto.ClienteDTO;
import com.riquetti.ProjetoLocadora.entity.ClienteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

    ClienteDTO toDTO(ClienteEntity clienteEntity);

    ClienteEntity toEntity(ClienteDTO clienteDTO);
}
