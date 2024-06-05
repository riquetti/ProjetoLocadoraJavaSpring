package com.riquetti.ProjetoLocadora.repository;

import com.riquetti.ProjetoLocadora.entity.FabricanteEntity;
import com.riquetti.ProjetoLocadora.entity.MunicipioEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MunicipioRepository {
    private final JdbcTemplate jdbcTemplate;

    public MunicipioRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<MunicipioEntity> findAll() {
        String sql = " SELECT m.id_municipio, m.descricao, m.id_estado, e.descricao as estado\n" +
                " FROM municipio m\n" +
                " INNER JOIN estado e ON e.id_estado = m.id_estado;";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(MunicipioEntity.class));
    }

    public Optional<MunicipioEntity> findById(Long id) {
        String sql =
                " SELECT m.id_municipio, m.descricao, m.id_estado, e.descricao as estado\n" +
                   " FROM municipio m\n" +
                   " INNER JOIN estado e ON e.id_estado = m.id_estado\n" +
                    "WHERE m.id_municipio = ?;";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(MunicipioEntity.class), id)
                .stream()
                .findFirst();
    }

}
