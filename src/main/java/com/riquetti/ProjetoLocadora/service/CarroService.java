package com.riquetti.ProjetoLocadora.service;

import com.riquetti.ProjetoLocadora.dto.CarroDTO;
import com.riquetti.ProjetoLocadora.entity.CarroEntity;
import com.riquetti.ProjetoLocadora.mapper.CarroMapper;
import com.riquetti.ProjetoLocadora.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarroService {
    @Autowired
    private CarroRepository carroRepository;

    public List<CarroDTO> findAll() {
        return carroRepository.findAll()
                .stream()
                .map(CarroMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<CarroDTO> findById(Long id) {
        return carroRepository.findById(id)
                .map(CarroMapper.INSTANCE::toDTO);
    }

    public Long save(CarroDTO carroDTO) {
        CarroEntity carroEntity = CarroMapper.INSTANCE.toEntity(carroDTO);
        return carroRepository.save(carroEntity);
    }

    public int update(CarroDTO carroDTO) {
        CarroEntity carroEntity = CarroMapper.INSTANCE.toEntity(carroDTO);
        return carroRepository.update(carroEntity);
    }

    public int deleteById(Long id) {
        return carroRepository.deleteById(id);
    }
}
