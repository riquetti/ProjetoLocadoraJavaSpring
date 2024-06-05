package com.riquetti.ProjetoLocadora.service;

import com.riquetti.ProjetoLocadora.dto.MunicipioDTO;
import com.riquetti.ProjetoLocadora.mapper.MunicipioMapper;
import com.riquetti.ProjetoLocadora.repository.MunicipioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MunicipioService {
    @Autowired
    private MunicipioRepository municipioRepository;

    @Autowired
    private MunicipioMapper municipioMapper;

    public List<MunicipioDTO> findAll() {
        return municipioRepository.findAll().stream()
                .map(municipioMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<MunicipioDTO> findById(Long id) {
        return municipioRepository.findById(id)
                .map(municipioMapper::toDTO);
    }
}
