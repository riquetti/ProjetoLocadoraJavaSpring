package com.riquetti.ProjetoLocadora.service;

import com.riquetti.ProjetoLocadora.entity.FabricanteEntity;
import com.riquetti.ProjetoLocadora.repository.FabricanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FabricanteService {

    @Autowired
    private FabricanteRepository fabricanteRepository;

    public List<FabricanteEntity> findAll() {
        return fabricanteRepository.findAll();
    }

    public Optional<FabricanteEntity> findById(Long id) {
        return fabricanteRepository.findById(id);
    }

    public Long save(FabricanteEntity fabricante) {
        return fabricanteRepository.save(fabricante);
    }

    public int update(FabricanteEntity fabricante) {
        return fabricanteRepository.update(fabricante);
    }

    public int deleteById(Long id) {
        return fabricanteRepository.deleteById(id);
    }

}
