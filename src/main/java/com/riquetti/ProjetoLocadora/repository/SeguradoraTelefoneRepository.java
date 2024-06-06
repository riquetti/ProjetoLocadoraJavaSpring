package com.riquetti.ProjetoLocadora.repository;

import com.riquetti.ProjetoLocadora.entity.SeguradoraTelefoneEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class SeguradoraTelefoneRepository {
    private final JdbcTemplate jdbcTemplate;

    public SeguradoraTelefoneRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<SeguradoraTelefoneEntity> findAll() {
        String sql = "SELECT s.id_seguradora_telefone, s.id_seguradora, s.telefone_seguradora, s2.nome as nomeSeguradora\n" +
                "FROM seguradoratelefone s\n" +
                "INNER JOIN seguradora s2 ON s2.id_seguradora  = s.id_seguradora;";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(SeguradoraTelefoneEntity.class));
    }

    public Optional<SeguradoraTelefoneEntity> findById(Long id) {
        String sql = "SELECT s.id_seguradora_telefone, s.id_seguradora, s.telefone_seguradora, s2.nome as nomeSeguradora\n" +
                "FROM seguradoratelefone s\n" +
                "INNER JOIN seguradora s2 ON s2.id_seguradora  = s.id_seguradora" +
                " WHERE s.id_seguradora_telefone = ?;";  // Corrigido para filtrar pelo ID do carro
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(SeguradoraTelefoneEntity.class), id)
                .stream()
                .findFirst();
    }

    public Long save(SeguradoraTelefoneEntity seguradoraTelefone) {

        String sqlInsertSeguradoraTelefone = "INSERT INTO seguradoratelefone (id_seguradora, telefone_seguradora) " +
                "VALUES (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sqlInsertSeguradoraTelefone, new String[]{"id_seguradora_telefone"});
            ps.setLong(1, seguradoraTelefone.getIdSeguradora());
            ps.setString(2, seguradoraTelefone.getTelefoneSeguradora());

            return ps;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    public int update(SeguradoraTelefoneEntity seguradoraTelefone) {

        String sql = "UPDATE seguradoratelefone SET id_seguradora = ?, telefone_seguradora = ? WHERE id_seguradora_telefone = ?";
        return jdbcTemplate.update(
                sql, seguradoraTelefone.getIdSeguradora(), seguradoraTelefone.getTelefoneSeguradora(),  seguradoraTelefone.getIdSeguradoraTelefone());
    }

    public int deleteById(Long id) {
        String sql = "DELETE FROM seguradoratelefone WHERE id_seguradora_telefone = ?";
        return jdbcTemplate.update(sql, id);
    }

}
