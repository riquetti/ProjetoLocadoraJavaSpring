package com.riquetti.ProjetoLocadora.controller;

import com.riquetti.ProjetoLocadora.dto.FabricanteDTO;
import com.riquetti.ProjetoLocadora.service.FabricanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/fabricantes")
public class FabricanteController {

    @Autowired
    FabricanteService service;

    @GetMapping
    public ResponseEntity<List<FabricanteDTO>> buscarTodos() {
        List<FabricanteDTO> fabricantes = service.findAll();
        return ResponseEntity.ok(fabricantes);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<FabricanteDTO> buscarPorId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody FabricanteDTO fabricanteDTO) {
        Long id = service.save(fabricanteDTO);
        return ResponseEntity.ok("Fabricante salvo com sucesso!");
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Fabricante deletado com sucesso!");
    }
    @PatchMapping
    public ResponseEntity<String> update(@RequestBody FabricanteDTO fabricanteDTO) {
        service.update(fabricanteDTO);
        return ResponseEntity.ok("Fabricante atualizado com sucesso!");
    }

}
