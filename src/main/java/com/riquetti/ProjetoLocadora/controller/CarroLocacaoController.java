package com.riquetti.ProjetoLocadora.controller;

import com.riquetti.ProjetoLocadora.entity.LocacaoEntity;
import com.riquetti.ProjetoLocadora.service.CarroLocacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carro-locacao")
public class CarroLocacaoController {

    private final CarroLocacaoService carroLocacaoService;

    public CarroLocacaoController(CarroLocacaoService carroLocacaoService) {
        this.carroLocacaoService = carroLocacaoService;
    }

    @PostMapping
    public ResponseEntity<String> createLocacaoIfCarAvailable(@RequestBody LocacaoEntity locacao) {
        try {
            LocacaoEntity savedLocacao = carroLocacaoService.createLocacaoIfCarAvailable(locacao);
            return ResponseEntity.status(HttpStatus.CREATED).body("Locação cadastrada com sucesso. ID: " + savedLocacao.getIdLocacao());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
