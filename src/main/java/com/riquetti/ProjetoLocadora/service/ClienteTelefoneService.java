package com.riquetti.ProjetoLocadora.service;

import com.riquetti.ProjetoLocadora.dto.ClienteTelefoneDTO;
import com.riquetti.ProjetoLocadora.entity.ClienteTelefoneEntity;
import com.riquetti.ProjetoLocadora.mapper.ClienteTelefoneMapper;
import com.riquetti.ProjetoLocadora.repository.ClienteTelefoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteTelefoneService {

    @Autowired
    private ClienteTelefoneRepository clienteTelefoneRepository;

    public List<ClienteTelefoneDTO> findAll() {
        return clienteTelefoneRepository.findAll()
                .stream()
                .map(ClienteTelefoneMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<ClienteTelefoneDTO> findById(Long id) {
        return clienteTelefoneRepository.findById(id)
                .map(ClienteTelefoneMapper.INSTANCE::toDTO);
    }

    public Long save(ClienteTelefoneDTO clienteTelefoneDTO) {
        ClienteTelefoneEntity carroEntity = ClienteTelefoneMapper.INSTANCE.toEntity(clienteTelefoneDTO);
        return clienteTelefoneRepository.save(carroEntity);
    }

    public int update(ClienteTelefoneDTO clienteTelefoneDTO) {
        ClienteTelefoneEntity clienteTelefoneEntity = ClienteTelefoneMapper.INSTANCE.toEntity(clienteTelefoneDTO);
        return clienteTelefoneRepository.update(clienteTelefoneEntity);
    }

    public int deleteById(Long id) {
        return clienteTelefoneRepository.deleteById(id);
    }
}
