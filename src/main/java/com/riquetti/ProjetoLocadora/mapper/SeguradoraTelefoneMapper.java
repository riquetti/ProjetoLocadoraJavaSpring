package com.riquetti.ProjetoLocadora.mapper;

import com.riquetti.ProjetoLocadora.dto.ClienteTelefoneDTO;
import com.riquetti.ProjetoLocadora.dto.SeguradoraTelefoneDTO;
import com.riquetti.ProjetoLocadora.entity.ClienteTelefoneEntity;
import com.riquetti.ProjetoLocadora.entity.SeguradoraTelefoneEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SeguradoraTelefoneMapper {
    SeguradoraTelefoneMapper INSTANCE = Mappers.getMapper(SeguradoraTelefoneMapper.class);

    SeguradoraTelefoneDTO toDTO(SeguradoraTelefoneEntity seguradoraTelefoneEntity);

    SeguradoraTelefoneEntity toEntity(SeguradoraTelefoneDTO seguradoraTelefoneDTO);
}
