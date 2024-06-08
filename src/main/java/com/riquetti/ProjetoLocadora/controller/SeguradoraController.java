package com.riquetti.ProjetoLocadora.controller;

import com.riquetti.ProjetoLocadora.dto.CarroDTO;
import com.riquetti.ProjetoLocadora.dto.ClienteDTO;
import com.riquetti.ProjetoLocadora.dto.SeguradoraDTO;
import com.riquetti.ProjetoLocadora.service.CarroService;
import com.riquetti.ProjetoLocadora.service.SeguradoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/seguradoras")
public class SeguradoraController {
    @Autowired
    private SeguradoraService seguradoraService;

    @GetMapping
    public ResponseEntity<List<SeguradoraDTO>> getAllSeguradoras() {
        List<SeguradoraDTO> seguradoras = seguradoraService.findAll();
        return ResponseEntity.ok(seguradoras);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getClienteById(@PathVariable Long id) {
        Optional<SeguradoraDTO> seguradoras = seguradoraService.findById(id);
        if (seguradoras.isPresent()) {
            return ResponseEntity.ok(seguradoras.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Seguradora não encontrado");
        }
    }

    @PostMapping
    public ResponseEntity<String> createSeguradora(@RequestBody SeguradoraDTO seguradoraDTO) {
        Long id = seguradoraService.save(seguradoraDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Seguradora cadastrado com sucesso. ID: " + id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateSeguradora(@PathVariable Long id, @RequestBody SeguradoraDTO updatedSeguradoraDTO) {
        if (id.equals(updatedSeguradoraDTO.idSeguradora())) {
            Optional<SeguradoraDTO> existingSeguradora = seguradoraService.findById(id);
            if (existingSeguradora.isPresent()) {
                seguradoraService.update(updatedSeguradoraDTO);
                return ResponseEntity.ok("Seguradora atualizado com sucesso");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Seguradora não encontrado");
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID no corpo do JSON não corresponde ao ID na URL");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSeguradora(@PathVariable Long id) {
        if (seguradoraService.findById(id).isPresent()) {
            seguradoraService.deleteById(id);
            return ResponseEntity.ok("Seguradora excluído com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Seguradora não encontrado");
        }
    }

}
