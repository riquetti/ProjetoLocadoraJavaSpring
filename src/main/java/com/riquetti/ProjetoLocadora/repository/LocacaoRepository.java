package com.riquetti.ProjetoLocadora.repository;

import com.riquetti.ProjetoLocadora.entity.FabricanteEntity;
import com.riquetti.ProjetoLocadora.entity.LocacaoEntity;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class LocacaoRepository {

    private final JdbcTemplate jdbcTemplate;

    public LocacaoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<LocacaoEntity> findAll() {
        String sql = "SELECT \n" +
                "    l.id_locacao,\n" +
                "    l.dataLocacao,\n" +
                "    l.dataDevolucao,\n" +
                "    l.dataDevolvida,\n" +
                "    l.valor,\n" +
                "    l.valorDesconto,\n" +
                "    l.valorTotal,\n" +
                "    l.id_carro,\n" +
                "    c.placa AS placaCarro,\n" +
                "    l.id_seguradora,\n" +
                "    s.cnpj  AS cnpjSeguradora,\n" +
                "    l.id_cliente,\n" +
                "    cl.cpf AS cpfCliente\n" +
                "FROM \n" +
                "    Locacao l\n" +
                "JOIN \n" +
                "    Carro c ON l.id_carro  = c.id\n" +
                "JOIN \n" +
                "    Seguradora s ON l.id_seguradora = s.id_seguradora\n" +
                "JOIN \n" +
                "    Cliente cl ON l.id_cliente = cl.id;";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(LocacaoEntity.class));
    }

    public LocacaoEntity findById(Long id) {
        String sql = "SELECT \n" +
                "    l.id_locacao,\n" +
                "    l.dataLocacao,\n" +
                "    l.dataDevolucao,\n" +
                "    l.dataDevolvida,\n" +
                "    l.valor,\n" +
                "    l.valorDesconto,\n" +
                "    l.valorTotal,\n" +
                "    l.id_carro,\n" +
                "    c.placa AS placaCarro,\n" +
                "    l.id_seguradora,\n" +
                "    s.cnpj  AS cnpjSeguradora,\n" +
                "    l.id_cliente,\n" +
                "    cl.cpf AS cpfCliente\n" +
                "FROM \n" +
                "    Locacao l\n" +
                "JOIN \n" +
                "    Carro c ON l.id_carro  = c.id\n" +
                "JOIN \n" +
                "    Seguradora s ON l.id_seguradora = s.id_seguradora\n" +
                "JOIN \n" +
                "    Cliente cl ON l.id_cliente = cl.id\n" +
                "WHERE \n" +
                "    l.id_locacao = ?;";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(LocacaoEntity.class), id);
    }


    public Long save(LocacaoEntity locacao) {
        String sql = "INSERT INTO Locacao (\n" +
                "    dataLocacao, dataDevolucao, dataDevolvida, valor, valorDesconto, valorTotal, id_carro, id_seguradora, id_cliente\n" +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[] {"id_locacao"});
            ps.setDate(1, java.sql.Date.valueOf(locacao.getDataLocacao()));
            ps.setDate(2, java.sql.Date.valueOf(locacao.getDataDevolucao()));
            ps.setDate(3, java.sql.Date.valueOf(locacao.getDataDevolvida()));
            ps.setBigDecimal(4, locacao.getValor());
            ps.setBigDecimal(5, locacao.getValorDesconto());
            ps.setBigDecimal(6, locacao.getValorTotal());
            ps.setLong(7, locacao.getIdCarro());
            ps.setLong(8, locacao.getIdSeguradora());
            ps.setLong(9, locacao.getIdCliente());
            return ps;
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            return keyHolder.getKey().longValue();
        } else {
            throw new InvalidDataAccessApiUsageException("Chave gerada não encontrada.");
        }
    }


    public int update(LocacaoEntity locacao) {
        String sql = "UPDATE Locacao SET \n" +
                "    dataLocacao = ?,\n" +
                "    dataDevolucao = ?,\n" +
                "    dataDevolvida = ?,\n" +
                "    valor = ?,\n" +
                "    valorDesconto = ?,\n" +
                "    valorTotal = ?,\n" +
                "    id_carro = ?,\n" +
                "    id_seguradora = ?,\n" +
                "    id_cliente = ?\n" +
                "WHERE id_locacao = ?";
        return jdbcTemplate.update(sql, locacao.getDataLocacao(), locacao.getDataDevolucao(), locacao.getDataDevolvida(),
                locacao.getValor(), locacao.getValorDesconto(), locacao.getValorTotal(), locacao.getIdCarro(),
                locacao.getIdSeguradora(), locacao.getIdCliente(), locacao.getIdLocacao());
    }

    public int deleteById(Long id) {
        String sql = "DELETE FROM Locacao WHERE id_locacao = ?";
        return jdbcTemplate.update(sql, id);
    }

}
