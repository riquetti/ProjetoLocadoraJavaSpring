package com.riquetti.ProjetoLocadora.controller;

import com.riquetti.ProjetoLocadora.dto.MunicipioDTO;
import com.riquetti.ProjetoLocadora.service.MunicipioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/municipios")
public class MunicipioController {
    @Autowired
    private MunicipioService municipioService;

    @GetMapping
    public ResponseEntity<List<MunicipioDTO>> getAllMunicipio() {
        List<MunicipioDTO> municipios = municipioService.findAll();
        return ResponseEntity.ok(municipios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getFabricanteById(@PathVariable Long id) {
        Optional<MunicipioDTO> municipios = municipioService.findById(id);
        if (municipios.isPresent()) {
            return ResponseEntity.ok(municipios.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Município não encontrado");
        }
    }
}
