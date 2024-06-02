package com.riquetti.ProjetoLocadora.controller;

import com.riquetti.ProjetoLocadora.entity.FabricanteEntity;
import com.riquetti.ProjetoLocadora.service.FabricanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/api/v1/fabricantes")
public class FabricanteController {

    @Autowired
    private FabricanteService fabricanteService;

    @GetMapping
    public ResponseEntity<List<FabricanteEntity>> getAllFabricantes() {
        List<FabricanteEntity> fabricantes = fabricanteService.findAll();
        return ResponseEntity.ok(fabricantes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getFabricanteById(@PathVariable Long id) {
        Optional<FabricanteEntity> fabricante = fabricanteService.findById(id);
        if (fabricante.isPresent()) {
            return ResponseEntity.ok(fabricante.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fabricante não encontrado");
        }
    }

    @PostMapping
    public ResponseEntity<String> createFabricante(@RequestBody FabricanteEntity fabricante) {
        Long id = fabricanteService.save(fabricante);
        return ResponseEntity.status(HttpStatus.CREATED).body("Fabricante cadastrado com sucesso. ID: " + id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateFabricante(@PathVariable Long id, @RequestBody FabricanteEntity updatedFabricante) {
        if (id.equals(updatedFabricante.getId())) {
            Optional<FabricanteEntity> existingFabricante = fabricanteService.findById(id);
            if (existingFabricante.isPresent()) {
                fabricanteService.update(updatedFabricante);
                return ResponseEntity.ok("Fabricante atualizado com sucesso");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fabricante não encontrado");
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID no corpo do JSON não corresponde ao ID na URL");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFabricante(@PathVariable Long id) {
        if (fabricanteService.findById(id).isPresent()) {
            fabricanteService.deleteById(id);
            return ResponseEntity.ok("Fabricante excluído com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fabricante não encontrado");
        }
    }
}
