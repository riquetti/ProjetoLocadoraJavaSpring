package com.riquetti.ProjetoLocadora.service;

import com.riquetti.ProjetoLocadora.entity.LocacaoEntity;
import com.riquetti.ProjetoLocadora.repository.LocacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocacaoService {

    @Autowired
    private LocacaoRepository locacaoRepository;

    public List<LocacaoEntity> findAll() {
        return locacaoRepository.findAll();
    }

    public Optional<LocacaoEntity> findById(Long id) {
        return Optional.ofNullable(locacaoRepository.findById(id));
    }

    public Long save(LocacaoEntity locacao) {
        return locacaoRepository.save(locacao);
    }

    public void update(LocacaoEntity locacao) {
        locacaoRepository.update(locacao);
    }

    public void deleteById(Long id) {
        locacaoRepository.deleteById(id);
    }
}
