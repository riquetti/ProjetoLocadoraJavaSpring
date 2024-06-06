package com.riquetti.ProjetoLocadora.service;

import com.riquetti.ProjetoLocadora.dto.SeguradoraTelefoneDTO;
import com.riquetti.ProjetoLocadora.entity.SeguradoraTelefoneEntity;
import com.riquetti.ProjetoLocadora.mapper.SeguradoraTelefoneMapper;
import com.riquetti.ProjetoLocadora.repository.SeguradoraTelefoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SeguradoraTelefoneService {
    @Autowired
    private SeguradoraTelefoneRepository seguradoraTelefoneRepository;

    public List<SeguradoraTelefoneDTO> findAll() {
        return seguradoraTelefoneRepository.findAll()
                .stream()
                .map(SeguradoraTelefoneMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<SeguradoraTelefoneDTO> findById(Long id) {
        return seguradoraTelefoneRepository.findById(id)
                .map(SeguradoraTelefoneMapper.INSTANCE::toDTO);
    }

    public Long save(SeguradoraTelefoneDTO seguradoraTelefoneDTO) {
        SeguradoraTelefoneEntity seguradoraTelefoneEntity = SeguradoraTelefoneMapper.INSTANCE.toEntity(seguradoraTelefoneDTO);
        return seguradoraTelefoneRepository.save(seguradoraTelefoneEntity);
    }

    public int update(SeguradoraTelefoneDTO seguradoraTelefoneDTO) {
        SeguradoraTelefoneEntity seguradoraTelefoneEntity = SeguradoraTelefoneMapper.INSTANCE.toEntity(seguradoraTelefoneDTO);
        return seguradoraTelefoneRepository.update(seguradoraTelefoneEntity);
    }

    public int deleteById(Long id) {
        return seguradoraTelefoneRepository.deleteById(id);
    }
}
