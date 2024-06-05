package com.riquetti.ProjetoLocadora.controller;

import com.riquetti.ProjetoLocadora.dto.EstadoDTO;
import com.riquetti.ProjetoLocadora.service.EstadoService;
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
@RequestMapping("/api/v1/estados")
public class EstadoController {
    @Autowired
    private EstadoService estadoService;

    @GetMapping
    public ResponseEntity<List<EstadoDTO>> getAllEstado() {
        List<EstadoDTO> estados = estadoService.findAll();
        return ResponseEntity.ok(estados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getFabricanteById(@PathVariable Long id) {
        Optional<EstadoDTO> estados = estadoService.findById(id);
        if (estados.isPresent()) {
            return ResponseEntity.ok(estados.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estado não encontrado");
        }
    }
}
