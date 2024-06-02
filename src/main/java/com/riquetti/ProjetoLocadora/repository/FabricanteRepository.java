package com.riquetti.ProjetoLocadora.repository;

import com.riquetti.ProjetoLocadora.entity.FabricanteEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class FabricanteRepository {
    private final JdbcTemplate jdbcTemplate;

    public FabricanteRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<FabricanteEntity> findAll() {
        String sql = "SELECT * FROM fabricante";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(FabricanteEntity.class));
    }

    public Optional<FabricanteEntity> findById(Long id) {
        String sql = "SELECT * FROM fabricante WHERE id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(FabricanteEntity.class), id)
                .stream()
                .findFirst();
    }

    public Long save(FabricanteEntity fabricante) {
        String sql = "INSERT INTO fabricante (nome) VALUES (?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, fabricante.getNome());
            return ps;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    public int update(FabricanteEntity fabricante) {
        String sql = "UPDATE fabricante SET nome = ? WHERE id = ?";
        return jdbcTemplate.update(sql, fabricante.getNome(), fabricante.getId());
    }

    public int deleteById(Long id) {
        String sql = "DELETE FROM fabricante WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
