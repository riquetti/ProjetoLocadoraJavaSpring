package com.riquetti.ProjetoLocadora.service;

import com.riquetti.ProjetoLocadora.entity.LocacaoEntity;
import com.riquetti.ProjetoLocadora.repository.CarroDevolucaoRepository;
import com.riquetti.ProjetoLocadora.repository.CarroLocacaoRepository;
import org.springframework.stereotype.Service;

@Service
public class CarroDevolucaoService {

    private final CarroDevolucaoRepository carroDevolucaoRepository;

    public CarroDevolucaoService(CarroDevolucaoRepository carroDevolucaoRepository) {
        this.carroDevolucaoRepository = carroDevolucaoRepository;
    }

    public LocacaoEntity createdevolucao(LocacaoEntity locacao) {
        return carroDevolucaoRepository.createDevolucaoIfCarAvailable(locacao);
    }
}
