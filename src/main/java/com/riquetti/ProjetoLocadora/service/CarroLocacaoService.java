package com.riquetti.ProjetoLocadora.service;

import com.riquetti.ProjetoLocadora.entity.LocacaoEntity;
import com.riquetti.ProjetoLocadora.repository.CarroLocacaoRepository;
import org.springframework.stereotype.Service;

@Service
public class CarroLocacaoService {

    private final CarroLocacaoRepository carroLocacaoRepository;

    public CarroLocacaoService(CarroLocacaoRepository carroLocacaoRepository) {
        this.carroLocacaoRepository = carroLocacaoRepository;
    }

    public LocacaoEntity createLocacaoIfCarAvailable(LocacaoEntity locacao) {
        return carroLocacaoRepository.createLocacao(locacao);
    }
}
