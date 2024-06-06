package com.riquetti.ProjetoLocadora.repository;

import com.riquetti.ProjetoLocadora.entity.ClienteTelefoneEntity;
import com.riquetti.ProjetoLocadora.exception.ClienteNotFoundException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class ClienteTelefoneRepository {
    private final JdbcTemplate jdbcTemplate;

    public ClienteTelefoneRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ClienteTelefoneEntity> findAll() {
        String sql = "SELECT ct.id_telefone, ct.id_cliente, ct.telefone, c.cpf as cpfCliente " +
                "FROM clientetelefone ct " +
                "INNER JOIN cliente c ON ct.id_cliente = c.id";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ClienteTelefoneEntity.class));
    }

    public Optional<ClienteTelefoneEntity> findById(Long id) {
        String sql = "SELECT ct.id_telefone, ct.id_cliente, ct.telefone, c.cpf as cpfCliente " +
                "FROM clientetelefone ct " +
                "INNER JOIN cliente c ON ct.id_cliente = c.id " +
                "WHERE ct.id_telefone = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ClienteTelefoneEntity.class), id)
                .stream()
                .findFirst();
    }

    public boolean existsCliente(Long idCliente) {
        String sql = "SELECT COUNT(*) FROM cliente WHERE id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, idCliente);
        return count != null && count > 0;
    }

    public Long save(ClienteTelefoneEntity clienteTelefone) {
        if (!existsCliente(clienteTelefone.getIdCliente())) {
            throw new ClienteNotFoundException(clienteTelefone.getIdCliente());
        }
        String sqlInsertClienteTelefone = "INSERT INTO clientetelefone (id_cliente, telefone) " +
                "VALUES (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sqlInsertClienteTelefone, new String[]{"id_telefone"});
            ps.setLong(1, clienteTelefone.getIdCliente());
            ps.setString(2, clienteTelefone.getTelefone());
            return ps;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    public int update(ClienteTelefoneEntity clienteTelefone) {
        if (!existsCliente(clienteTelefone.getIdCliente())) {
            throw new ClienteNotFoundException(clienteTelefone.getIdCliente());
        }
        String sql = "UPDATE clientetelefone SET id_cliente = ?, telefone = ? WHERE id_telefone = ?";
        return jdbcTemplate.update(
                sql, clienteTelefone.getIdCliente(), clienteTelefone.getTelefone(), clienteTelefone.getIdTelefone());
    }

    public int deleteById(Long id) {
        String sql = "DELETE FROM clientetelefone WHERE id_telefone = ?";
        return jdbcTemplate.update(sql, id);
    }

}
