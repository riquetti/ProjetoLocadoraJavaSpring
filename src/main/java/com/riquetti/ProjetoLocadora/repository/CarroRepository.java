package com.riquetti.ProjetoLocadora.repository;

import com.riquetti.ProjetoLocadora.entity.CarroEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class CarroRepository {
    private final JdbcTemplate jdbcTemplate;

    public CarroRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<CarroEntity> findAll() {
        String sql = "SELECT c.id, c.placa, c.cor, c.disponivel, c.ano,\n" +
                " c.valorlocacao, c.id_fabricante, c.id_modelo,\n" +
                " f.nome AS fabricante, m.nome AS modelo\n" +
                " FROM carro c\n" +
                " INNER JOIN fabricante f ON c.id_fabricante = f.id\n" +
                " INNER JOIN modelo m ON c.id_modelo = m.id;";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CarroEntity.class));
    }

    public Optional<CarroEntity> findById(Long id) {
        String sql = "SELECT c.id, c.placa, c.cor, c.disponivel, c.ano,\n" +
                " c.valorlocacao, c.id_fabricante, c.id_modelo,\n" +
                " f.nome AS fabricante, m.nome AS modelo\n" +
                " FROM carro c\n" +
                " INNER JOIN fabricante f ON c.id_fabricante = f.id\n" +
                " INNER JOIN modelo m ON c.id_modelo = m.id\n" +
                " WHERE c.id = ?";  // Corrigido para filtrar pelo ID do carro
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CarroEntity.class), id)
                .stream()
                .findFirst();
    }

    public Long save(CarroEntity carro) {
        // Primeiro, obtenha o id_fabricante a partir do id_modelo
        Long idFabricante = getIdFabricanteFromModelo(carro.getIdModelo());

        // Em seguida, insira o novo carro com o id_fabricante obtido
        String sqlInsertCarro = "INSERT INTO carro (placa, cor, disponivel, ano, valorlocacao, id_fabricante, id_modelo) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sqlInsertCarro, new String[]{"id"});
            ps.setString(1, carro.getPlaca());
            ps.setString(2, carro.getCor());
            ps.setBoolean(3, carro.isDisponivel());
            ps.setLong(4, carro.getAno());
            ps.setLong(5, carro.getValorLocacao());
            ps.setLong(6, idFabricante);
            ps.setLong(7, carro.getIdModelo());
            return ps;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    public int update(CarroEntity carro) {
        // Primeiro, obtenha o id_fabricante a partir do id_modelo
        Long idFabricante = getIdFabricanteFromModelo(carro.getIdModelo());

        // Em seguida, atualize o carro com o id_fabricante obtido
        String sql = "UPDATE carro SET placa = ?, cor = ?, disponivel = ?, ano = ?, valorlocacao = ?, id_fabricante = ?, id_modelo = ? WHERE id = ?";
        return jdbcTemplate.update(
                sql, carro.getPlaca(), carro.getCor(), carro.isDisponivel(), carro.getAno(),
                carro.getValorLocacao(),idFabricante, carro.getIdModelo(), carro.getId());
    }

    public int deleteById(Long id) {
        String sql = "DELETE FROM carro WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    private Long getIdFabricanteFromModelo(Long idModelo) {
        String sqlGetFabricante = "SELECT id_fabricante FROM modelo WHERE id = ?";
        return jdbcTemplate.queryForObject(sqlGetFabricante, new Object[]{idModelo}, Long.class);
    }
}