package com.riquetti.ProjetoLocadora.service;

import com.riquetti.ProjetoLocadora.dto.EstadoDTO;
import com.riquetti.ProjetoLocadora.dto.FabricanteDTO;
import com.riquetti.ProjetoLocadora.mapper.EstadoMapper;
import com.riquetti.ProjetoLocadora.mapper.FabricanteMapper;
import com.riquetti.ProjetoLocadora.repository.EstadoRepository;
import com.riquetti.ProjetoLocadora.repository.FabricanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EstadoService {
    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private EstadoMapper estadoMapper;

    public List<EstadoDTO> findAll() {
        return estadoRepository.findAll().stream()
                .map(estadoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<EstadoDTO> findById(Long id) {
        return estadoRepository.findById(id)
                .map(estadoMapper::toDTO);
    }
}
