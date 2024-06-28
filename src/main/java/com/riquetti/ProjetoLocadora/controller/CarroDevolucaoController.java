package com.riquetti.ProjetoLocadora.controller;

import com.riquetti.ProjetoLocadora.entity.LocacaoEntity;
import com.riquetti.ProjetoLocadora.service.CarroDevolucaoService;
import com.riquetti.ProjetoLocadora.service.CarroLocacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carro-devolucao")
public class CarroDevolucaoController {

    private final CarroDevolucaoService carroDevolucaoService;

    public CarroDevolucaoController(CarroDevolucaoService carroDevolucaoService) {
        this. carroDevolucaoService =  carroDevolucaoService;
    }

    @PostMapping
    public ResponseEntity<String> createDevolucao(@RequestBody LocacaoEntity devolucao) {
        try {
            LocacaoEntity savedDevolucao = carroDevolucaoService.createdevolucao(devolucao);
            return ResponseEntity.status(HttpStatus.CREATED).body("Devolução realizada com sucesso. ID: " + savedDevolucao.getIdLocacao());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
