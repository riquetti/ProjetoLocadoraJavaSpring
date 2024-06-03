package com.riquetti.ProjetoLocadora.service;

import com.riquetti.ProjetoLocadora.dto.FabricanteDTO;
import com.riquetti.ProjetoLocadora.dto.ModeloDTO;
import com.riquetti.ProjetoLocadora.entity.FabricanteEntity;
import com.riquetti.ProjetoLocadora.entity.ModeloEntity;
import com.riquetti.ProjetoLocadora.mapper.FabricanteMapper;
import com.riquetti.ProjetoLocadora.mapper.ModeloMapper;
import com.riquetti.ProjetoLocadora.repository.FabricanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FabricanteService {

    @Autowired
    private FabricanteRepository fabricanteRepository;

    @Autowired
    private FabricanteMapper fabricanteMapper;

    public List<FabricanteDTO> findAll() {
        return fabricanteRepository.findAll().stream()
                .map(fabricanteMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<FabricanteDTO> findById(Long id) {
        return fabricanteRepository.findById(id)
                .map(fabricanteMapper::toDTO);
    }

    public Long save(FabricanteDTO fabricanteDTO) {
        FabricanteEntity fabricanteEntity = fabricanteMapper.toEntity(fabricanteDTO);
        return fabricanteRepository.save(fabricanteEntity);
    }

    public int update(FabricanteDTO fabricanteDTO) {
        FabricanteEntity fabricanteEntity = fabricanteMapper.toEntity(fabricanteDTO);
        return fabricanteRepository.update(fabricanteEntity);
    }

    public int deleteById(Long id) {
        return fabricanteRepository.deleteById(id);
    }

}
