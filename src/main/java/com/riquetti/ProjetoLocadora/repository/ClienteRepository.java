package com.riquetti.ProjetoLocadora.repository;

import com.riquetti.ProjetoLocadora.entity.CarroEntity;
import com.riquetti.ProjetoLocadora.entity.ClienteEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class ClienteRepository {
    private final JdbcTemplate jdbcTemplate;

    public ClienteRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ClienteEntity> findAll() {
        String sql = "SELECT c.id, c.nome_cliente , c.rg, c.cpf, c.logradouro,\n" +
                "       c.id_municipio, c.id_estado, c.cnh, c.cnhdatavencimento ,\n" +
                "       c.email_cliente,\n" +
                "       m.descricao AS municipio, e.descricao  AS estado\n" +
                "FROM cliente c\n" +
                "INNER JOIN municipio m ON c.id_municipio = m.id_municipio\n" +
                "INNER JOIN estado e ON c.id_estado = e.id_estado;";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ClienteEntity.class));
    }

    public Optional<ClienteEntity> findById(Long id) {
        String sql = "SELECT c.id, c.nome_cliente , c.rg, c.cpf, c.logradouro,\n" +
                "       c.id_municipio, c.id_estado, c.cnh, c.cnhdatavencimento ,\n" +
                "       c.email_cliente,\n" +
                "       m.descricao AS municipio, e.descricao  AS estado\n" +
                "FROM cliente c\n" +
                "INNER JOIN municipio m ON c.id_municipio = m.id_municipio\n" +
                "INNER JOIN estado e ON c.id_estado = e.id_estado"+
                " WHERE c.id = ?";  // Corrigido para filtrar pelo ID do carro
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ClienteEntity.class), id)
                .stream()
                .findFirst();
    }

    public Long save(ClienteEntity cliente) {

        String sqlInsertCliente= "INSERT INTO cliente (nome_cliente, rg, cpf, logradouro, id_municipio, id_estado, cnh, cnhdatavencimento, email_cliente) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sqlInsertCliente, new String[]{"id"});
            ps.setString(1, cliente.getNomeCliente());
            ps.setString(2, cliente.getRg());
            ps.setString(3, cliente.getCpf());
            ps.setString(4, cliente.getLogradouro());
            ps.setLong(5, cliente.getIdMunicipio());
            ps.setLong(6, cliente.getIdEstado());
            ps.setString(7, cliente.getCnh());
            ps.setDate(8, java.sql.Date.valueOf(cliente.getCnhdataVencimento()));
            ps.setString(9, cliente.getEmailCliente());
            return ps;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    public int update(ClienteEntity cliente) {

        String sql = "UPDATE cliente SET nome_cliente = ?, rg = ?, cpf = ?, logradouro = ?, id_municipio = ?, id_estado = ?, cnh = ?, cnhdatavencimento = ?, email_cliente = ? WHERE id = ?";
        return jdbcTemplate.update(
                sql, cliente.getNomeCliente(), cliente.getRg(), cliente.getCpf(), cliente.getLogradouro(),
                cliente.getIdMunicipio(), cliente.getIdEstado(), cliente.getCnh(), cliente.getCnhdataVencimento(),
                cliente.getEmailCliente(), cliente.getId());
    }

    public int deleteById(Long id) {
        String sql = "DELETE FROM cliente WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

}
