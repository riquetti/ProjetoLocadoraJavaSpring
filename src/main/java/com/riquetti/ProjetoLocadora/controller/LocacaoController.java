package com.riquetti.ProjetoLocadora.controller;

import com.riquetti.ProjetoLocadora.entity.LocacaoEntity;
import com.riquetti.ProjetoLocadora.service.LocacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/locacoes")
public class LocacaoController {

    @Autowired
    private LocacaoService locacaoService;

    @GetMapping
    public ResponseEntity<List<LocacaoEntity>> getAllLocacoes() {
        List<LocacaoEntity> locacoes = locacaoService.findAll();
        return ResponseEntity.ok(locacoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getLocacaoById(@PathVariable Long id) {
        Optional<LocacaoEntity> locacao = locacaoService.findById(id);
        if (locacao.isPresent()) {
            return ResponseEntity.ok(locacao.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Locação não encontrada");
        }
    }

    @PostMapping
    public ResponseEntity<String> createLocacao(@RequestBody LocacaoEntity locacao) {
        Long id = locacaoService.save(locacao);
        return ResponseEntity.status(HttpStatus.CREATED).body("Locação cadastrada com sucesso. ID: " + id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateLocacao(@PathVariable Long id, @RequestBody LocacaoEntity updatedLocacao) {
        if (id.equals(updatedLocacao.getIdLocacao())) {
            Optional<LocacaoEntity> existingLocacao = locacaoService.findById(id);
            if (existingLocacao.isPresent()) {
                locacaoService.update(updatedLocacao);
                return ResponseEntity.ok("Locação atualizada com sucesso");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Locação não encontrada");
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID no corpo do JSON não corresponde ao ID na URL");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLocacao(@PathVariable Long id) {
        if (locacaoService.findById(id).isPresent()) {
            locacaoService.deleteById(id);
            return ResponseEntity.ok("Locação excluída com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Locação não encontrada");
        }
    }
}
