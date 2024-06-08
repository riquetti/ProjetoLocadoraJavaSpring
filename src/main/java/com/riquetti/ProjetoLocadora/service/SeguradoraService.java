package com.riquetti.ProjetoLocadora.service;

import com.riquetti.ProjetoLocadora.dto.CarroDTO;
import com.riquetti.ProjetoLocadora.dto.ClienteDTO;
import com.riquetti.ProjetoLocadora.dto.SeguradoraDTO;
import com.riquetti.ProjetoLocadora.entity.ClienteEntity;
import com.riquetti.ProjetoLocadora.entity.SeguradoraEntity;
import com.riquetti.ProjetoLocadora.mapper.CarroMapper;
import com.riquetti.ProjetoLocadora.mapper.ClienteMapper;
import com.riquetti.ProjetoLocadora.mapper.SeguradoraMapper;
import com.riquetti.ProjetoLocadora.repository.CarroRepository;
import com.riquetti.ProjetoLocadora.repository.SeguradoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SeguradoraService {
    @Autowired
    private SeguradoraRepository seguradoraRepository;

    public List<SeguradoraDTO> findAll() {
        return seguradoraRepository.findAll()
                .stream()
                .map(SeguradoraMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<SeguradoraDTO> findById(Long id) {
        return seguradoraRepository.findById(id)
                .map(SeguradoraMapper.INSTANCE::toDTO);
    }

    public Long save(SeguradoraDTO seguradoraDTO) {
        SeguradoraEntity seguradoraEntity = SeguradoraMapper.INSTANCE.toEntity(seguradoraDTO);
        return seguradoraRepository.save(seguradoraEntity);
    }

    public int update(SeguradoraDTO seguradoraDTO) {
        SeguradoraEntity seguradoraEntity = SeguradoraMapper.INSTANCE.toEntity(seguradoraDTO);
        return seguradoraRepository.update(seguradoraEntity);
    }

    public int deleteById(Long id) {
        return seguradoraRepository.deleteById(id);
    }

}
