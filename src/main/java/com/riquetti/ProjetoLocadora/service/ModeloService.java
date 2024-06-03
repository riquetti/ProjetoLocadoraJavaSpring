package com.riquetti.ProjetoLocadora.service;

import com.riquetti.ProjetoLocadora.dto.ModeloDTO;
import com.riquetti.ProjetoLocadora.entity.ModeloEntity;
import com.riquetti.ProjetoLocadora.mapper.ModeloMapper;
import com.riquetti.ProjetoLocadora.repository.ModeloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ModeloService {

    @Autowired
    private ModeloRepository modeloRepository;

    public List<ModeloDTO> findAll() {
        return modeloRepository.findAll()
                .stream()
                .map(ModeloMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<ModeloDTO> findById(Long id) {
        return modeloRepository.findById(id)
                .map(ModeloMapper.INSTANCE::toDTO);
    }

    public Long save(ModeloDTO modeloDTO) {
        ModeloEntity modeloEntity = ModeloMapper.INSTANCE.toEntity(modeloDTO);
        return modeloRepository.save(modeloEntity);
    }

    public int update(ModeloDTO modeloDTO) {
        ModeloEntity modeloEntity = ModeloMapper.INSTANCE.toEntity(modeloDTO);
        return modeloRepository.update(modeloEntity);
    }

    public int deleteById(Long id) {
        return modeloRepository.deleteById(id);
    }
}
