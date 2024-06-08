package com.riquetti.ProjetoLocadora.repository;

import com.riquetti.ProjetoLocadora.entity.CarroEntity;
import com.riquetti.ProjetoLocadora.entity.ClienteEntity;
import com.riquetti.ProjetoLocadora.entity.SeguradoraEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class SeguradoraRepository {

    private final JdbcTemplate jdbcTemplate;

    public SeguradoraRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<SeguradoraEntity> findAll() {
        String sql = "SELECT s.id_seguradora, s.nome, s.cnpj , s.email , s.valor , s.id_municipio, s.id_estado, m.descricao as municipio , e.descricao as estado \n" +
                "FROM seguradora s\n" +
                "INNER JOIN municipio m on s.id_municipio = m.id_municipio\n" +
                "INNER JOIN estado e on s.id_estado = e.id_estado;";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(SeguradoraEntity.class));
    }

    public Optional<SeguradoraEntity> findById(Long id) {
        String sql = "SELECT s.id_seguradora, s.nome, s.cnpj , s.email , s.valor , s.id_municipio, s.id_estado, m.descricao as municipio , e.descricao as estado \n" +
                "FROM seguradora s\n" +
                "INNER JOIN municipio m on s.id_municipio = m.id_municipio\n" +
                "INNER JOIN estado e on s.id_estado = e.id_estado\n" +
                "WHERE s.id_seguradora = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(SeguradoraEntity.class), id)
                .stream()
                .findFirst();
    }

    public Long save(SeguradoraEntity seguradora) {

        String sqlInsertSeguradora= "INSERT INTO seguradora (nome, cnpj, email, valor, id_municipio, id_estado) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sqlInsertSeguradora, new String[]{"id_seguradora"});
            ps.setString(1, seguradora.getNome());
            ps.setString(2, seguradora.getCnpj());
            ps.setString(3, seguradora.getEmail());
            ps.setLong(4, seguradora.getValor());
            ps.setLong(5, seguradora.getIdMunicipio());
            ps.setLong(6, seguradora.getIdEstado());
            return ps;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    public int update(SeguradoraEntity seguradora) {

        String sql = "UPDATE seguradora SET nome = ?, cnpj = ?, email = ?, valor = ?, id_municipio = ?, id_estado = ? WHERE id_seguradora = ?";
        return jdbcTemplate.update(
                sql, seguradora.getNome(), seguradora.getCnpj(), seguradora.getEmail(), seguradora.getValor(),
                seguradora.getIdMunicipio(), seguradora.getIdEstado(), seguradora.getIdSeguradora());
    }

    public int deleteById(Long id) {
        String sql = "DELETE FROM seguradora WHERE id_seguradora = ?";
        return jdbcTemplate.update(sql, id);
    }

}
