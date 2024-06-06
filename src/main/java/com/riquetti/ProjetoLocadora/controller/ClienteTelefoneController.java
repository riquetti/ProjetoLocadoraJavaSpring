package com.riquetti.ProjetoLocadora.controller;

import com.riquetti.ProjetoLocadora.dto.ClienteTelefoneDTO;
import com.riquetti.ProjetoLocadora.service.ClienteTelefoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/clientetelefones")
public class ClienteTelefoneController {
    @Autowired
    private ClienteTelefoneService clienteTelefoneService;

    @GetMapping
    public ResponseEntity<List<ClienteTelefoneDTO>> getAllClienteTelefone() {
        List<ClienteTelefoneDTO> clienteTelefone = clienteTelefoneService.findAll();
        return ResponseEntity.ok(clienteTelefone);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getClienteTelefoneById(@PathVariable Long id) {
        Optional<ClienteTelefoneDTO> clienteTelefone = clienteTelefoneService.findById(id);
        if (clienteTelefone.isPresent()) {
            return ResponseEntity.ok(clienteTelefone.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Telefone não encontrado");
        }
    }

    @PostMapping
    public ResponseEntity<String> createClienteTelefone(@RequestBody ClienteTelefoneDTO clienteTelefoneDTO) {
        Long id = clienteTelefoneService.save(clienteTelefoneDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Telefone cadastrado com sucesso. ID: " + id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateClienteTelefone(@PathVariable Long id, @RequestBody ClienteTelefoneDTO updatedClienteTelefoneDTO) {
        if (id.equals(updatedClienteTelefoneDTO.idTelefone())) {
            Optional<ClienteTelefoneDTO> existingClienteTelefone = clienteTelefoneService.findById(id);
            if (existingClienteTelefone.isPresent()) {
                clienteTelefoneService.update(updatedClienteTelefoneDTO);
                return ResponseEntity.ok("Telefone atualizado com sucesso");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Telefone não encontrado");
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID no corpo do JSON não corresponde ao ID na URL");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClienteTelefone(@PathVariable Long id) {
        if (clienteTelefoneService.findById(id).isPresent()) {
            clienteTelefoneService.deleteById(id);
            return ResponseEntity.ok("Telefone excluído com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Telefone não encontrado");
        }
    }
}
