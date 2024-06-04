package com.riquetti.ProjetoLocadora.controller;

import com.riquetti.ProjetoLocadora.dto.CarroDTO;
import com.riquetti.ProjetoLocadora.service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/carros")
public class CarroController {
    @Autowired
    private CarroService carroService;

    @GetMapping
    public ResponseEntity<List<CarroDTO>> getAllCarros() {
        List<CarroDTO> carros = carroService.findAll();
        return ResponseEntity.ok(carros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCarroById(@PathVariable Long id) {
        Optional<CarroDTO> carros = carroService.findById(id);
        if (carros.isPresent()) {
            return ResponseEntity.ok(carros.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carro não encontrado");
        }
    }

    @PostMapping
    public ResponseEntity<String> createCarro(@RequestBody CarroDTO carroDTO) {
        Long id = carroService.save(carroDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Carro cadastrado com sucesso. ID: " + id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCarro(@PathVariable Long id, @RequestBody CarroDTO updatedCarroDTO) {
        if (id.equals(updatedCarroDTO.id())) {
            Optional<CarroDTO> existingCarro = carroService.findById(id);
            if (existingCarro.isPresent()) {
                carroService.update(updatedCarroDTO);
                return ResponseEntity.ok("Carro atualizado com sucesso");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carro não encontrado");
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID no corpo do JSON não corresponde ao ID na URL");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteModelo(@PathVariable Long id) {
        if (carroService.findById(id).isPresent()) {
            carroService.deleteById(id);
            return ResponseEntity.ok("Carro excluído com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carro não encontrado");
        }
    }
}
