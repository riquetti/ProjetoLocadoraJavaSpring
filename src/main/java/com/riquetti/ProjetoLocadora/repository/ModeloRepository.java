package com.riquetti.ProjetoLocadora.repository;

import com.riquetti.ProjetoLocadora.entity.ModeloEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class ModeloRepository {
    private final JdbcTemplate jdbcTemplate;

    public ModeloRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ModeloEntity> findAll() {
        String sql = "SELECT m.id, m.id_fabricante, f.nome AS nomeFabricante, m.nome\n" +
                "FROM modelo m\n" +
                "INNER JOIN fabricante f ON m.id_fabricante = f.id";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ModeloEntity.class));
    }

    public Optional<ModeloEntity> findById(Long id) {
        String sql = "SELECT m.id, m.id_fabricante, f.nome AS nomeFabricante, m.nome\n" +
                "FROM modelo m\n" +
                "INNER JOIN fabricante f ON m.id_fabricante = f.id\n" +
                "WHERE m.id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ModeloEntity.class), id)
                .stream()
                .findFirst();
    }

    public Long save(ModeloEntity modelo) {
        String sql = "INSERT INTO modelo (id_fabricante, nome) VALUES (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setLong(1, modelo.getIdFabricante());
            ps.setString(2, modelo.getNome());
            return ps;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    public int update(ModeloEntity modelo) {
        String sql = "UPDATE modelo SET id_fabricante = ?, nome = ? WHERE id = ?";
        return jdbcTemplate.update(sql, modelo.getIdFabricante(), modelo.getNome(), modelo.getId());
    }

    public int deleteById(Long id) {
        String sql = "DELETE FROM modelo WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
