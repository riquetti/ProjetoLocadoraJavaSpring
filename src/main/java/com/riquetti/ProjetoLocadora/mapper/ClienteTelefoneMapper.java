package com.riquetti.ProjetoLocadora.mapper;

import com.riquetti.ProjetoLocadora.dto.CarroDTO;
import com.riquetti.ProjetoLocadora.dto.ClienteTelefoneDTO;
import com.riquetti.ProjetoLocadora.entity.CarroEntity;
import com.riquetti.ProjetoLocadora.entity.ClienteTelefoneEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClienteTelefoneMapper {
    ClienteTelefoneMapper INSTANCE = Mappers.getMapper(ClienteTelefoneMapper.class);

    ClienteTelefoneDTO toDTO(ClienteTelefoneEntity clienteTelefoneEntity);

    ClienteTelefoneEntity toEntity(ClienteTelefoneDTO clienteTelefoneDTO);
}
