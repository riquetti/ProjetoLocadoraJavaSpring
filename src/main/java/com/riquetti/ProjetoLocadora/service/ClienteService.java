package com.riquetti.ProjetoLocadora.service;

import com.riquetti.ProjetoLocadora.dto.CarroDTO;
import com.riquetti.ProjetoLocadora.dto.ClienteDTO;
import com.riquetti.ProjetoLocadora.entity.CarroEntity;
import com.riquetti.ProjetoLocadora.entity.ClienteEntity;
import com.riquetti.ProjetoLocadora.mapper.CarroMapper;
import com.riquetti.ProjetoLocadora.mapper.ClienteMapper;
import com.riquetti.ProjetoLocadora.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public List<ClienteDTO> findAll() {
        return clienteRepository.findAll()
                .stream()
                .map(ClienteMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<ClienteDTO> findById(Long id) {
        return clienteRepository.findById(id)
                .map(ClienteMapper.INSTANCE::toDTO);
    }

    public Long save(ClienteDTO clienteDTO) {
        ClienteEntity clienteEntity = ClienteMapper.INSTANCE.toEntity(clienteDTO);
        return clienteRepository.save(clienteEntity);
    }

    public int update(ClienteDTO clienteDTO) {
        ClienteEntity clienteEntity = ClienteMapper.INSTANCE.toEntity(clienteDTO);
        return clienteRepository.update(clienteEntity);
    }

    public int deleteById(Long id) {
        return clienteRepository.deleteById(id);
    }

}
