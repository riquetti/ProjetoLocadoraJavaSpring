package com.riquetti.ProjetoLocadora.controller;

import com.riquetti.ProjetoLocadora.dto.SeguradoraTelefoneDTO;
import com.riquetti.ProjetoLocadora.service.SeguradoraTelefoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/seguradoratelefones")
public class SeguradoraTelefoneController {
    @Autowired
    private SeguradoraTelefoneService seguradoraTelefoneService;

    @GetMapping
    public ResponseEntity<List<SeguradoraTelefoneDTO>> getAllSeguradoraTelefone() {
        List<SeguradoraTelefoneDTO> seguradoraTelefone = seguradoraTelefoneService.findAll();
        return ResponseEntity.ok(seguradoraTelefone);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getSeguradoraTelefoneById(@PathVariable Long id) {
        Optional<SeguradoraTelefoneDTO> seguradoraTelefone = seguradoraTelefoneService.findById(id);
        if (seguradoraTelefone.isPresent()) {
            return ResponseEntity.ok(seguradoraTelefone.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Telefone não encontrado");
        }
    }

    @PostMapping
    public ResponseEntity<String> createSeguradoraTelefone(@RequestBody SeguradoraTelefoneDTO seguradoraTelefoneDTO) {
        Long id = seguradoraTelefoneService.save(seguradoraTelefoneDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Telefone cadastrado com sucesso. ID: " + id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateSeguradoraTelefone(@PathVariable Long id, @RequestBody SeguradoraTelefoneDTO updatedSeguradoraTelefoneDTO) {
        if (id.equals(updatedSeguradoraTelefoneDTO.idSeguradoraTelefone())) {
            Optional<SeguradoraTelefoneDTO> existingSeguradoraTelefone = seguradoraTelefoneService.findById(id);
            if (existingSeguradoraTelefone.isPresent()) {
                seguradoraTelefoneService.update(updatedSeguradoraTelefoneDTO);
                return ResponseEntity.ok("Telefone atualizado com sucesso");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Telefone não encontrado");
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID no corpo do JSON não corresponde ao ID na URL");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSeguradoraTelefone(@PathVariable Long id) {
        if (seguradoraTelefoneService.findById(id).isPresent()) {
            seguradoraTelefoneService.deleteById(id);
            return ResponseEntity.ok("Telefone excluído com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Telefone não encontrado");
        }
    }
}
