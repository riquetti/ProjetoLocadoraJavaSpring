package com.riquetti.ProjetoLocadora.controller;

import com.riquetti.ProjetoLocadora.dto.ModeloDTO;
import com.riquetti.ProjetoLocadora.service.ModeloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/modelos")
public class ModeloController {

    @Autowired
    private ModeloService modeloService;

    @GetMapping
    public ResponseEntity<List<ModeloDTO>> getAllModelos() {
        List<ModeloDTO> modelos = modeloService.findAll();
        return ResponseEntity.ok(modelos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getModeloById(@PathVariable Long id) {
        Optional<ModeloDTO> modelo = modeloService.findById(id);
        if (modelo.isPresent()) {
            return ResponseEntity.ok(modelo.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Modelo não encontrado");
        }
    }

    @PostMapping
    public ResponseEntity<String> createModelo(@RequestBody ModeloDTO modeloDTO) {
        Long id = modeloService.save(modeloDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Modelo cadastrado com sucesso. ID: " + id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateModelo(@PathVariable Long id, @RequestBody ModeloDTO updatedModeloDTO) {
        if (id.equals(updatedModeloDTO.id())) {
            Optional<ModeloDTO> existingModelo = modeloService.findById(id);
            if (existingModelo.isPresent()) {
                modeloService.update(updatedModeloDTO);
                return ResponseEntity.ok("Modelo atualizado com sucesso");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Modelo não encontrado");
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID no corpo do JSON não corresponde ao ID na URL");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteModelo(@PathVariable Long id) {
        if (modeloService.findById(id).isPresent()) {
            modeloService.deleteById(id);
            return ResponseEntity.ok("Modelo excluído com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Modelo não encontrado");
        }
    }
}
