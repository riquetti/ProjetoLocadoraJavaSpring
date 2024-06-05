package com.riquetti.ProjetoLocadora.repository;

import com.riquetti.ProjetoLocadora.entity.EstadoEntity;
import com.riquetti.ProjetoLocadora.entity.FabricanteEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EstadoRepository {
    private final JdbcTemplate jdbcTemplate;

    public EstadoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<EstadoEntity> findAll() {
        String sql = "SELECT * FROM estado";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(EstadoEntity.class));
    }

    public Optional<EstadoEntity> findById(Long id) {
        String sql = "SELECT * FROM estado WHERE id_estado = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(EstadoEntity.class), id)
                .stream()
                .findFirst();
    }
}
