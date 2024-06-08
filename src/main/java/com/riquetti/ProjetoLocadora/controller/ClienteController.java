package com.riquetti.ProjetoLocadora.controller;

import com.riquetti.ProjetoLocadora.dto.ClienteDTO;
import com.riquetti.ProjetoLocadora.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> getAllCliente() {
        List<ClienteDTO> clientes = clienteService.findAll();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getClienteById(@PathVariable Long id) {
        Optional<ClienteDTO> clientes = clienteService.findById(id);
        if (clientes.isPresent()) {
            return ResponseEntity.ok(clientes.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carro não encontrado");
        }
    }

    @PostMapping
    public ResponseEntity<String> createCliente(@RequestBody ClienteDTO clienteDTO) {
        Long id = clienteService.save(clienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Cliente cadastrado com sucesso. ID: " + id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCarro(@PathVariable Long id, @RequestBody ClienteDTO updatedClienteDTO) {
        if (id.equals(updatedClienteDTO.id())) {
            Optional<ClienteDTO> existingCarro = clienteService.findById(id);
            if (existingCarro.isPresent()) {
                clienteService.update(updatedClienteDTO);
                return ResponseEntity.ok("CLiente atualizado com sucesso");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID no corpo do JSON não corresponde ao ID na URL");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCliente(@PathVariable Long id) {
        if (clienteService.findById(id).isPresent()) {
            clienteService.deleteById(id);
            return ResponseEntity.ok("Cliente excluído com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
        }
    }

}
