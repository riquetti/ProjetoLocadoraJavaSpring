package com.riquetti.ProjetoLocadora.mapper;

import com.riquetti.ProjetoLocadora.dto.LocacaoDTO;
import com.riquetti.ProjetoLocadora.entity.LocacaoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LocacaoMapper {

    LocacaoMapper INSTANCE = Mappers.getMapper(LocacaoMapper.class);

    LocacaoDTO toDTO(LocacaoEntity locacaoEntity);

    LocacaoEntity toEntity(LocacaoDTO locacaoDTO);
}
