package com.riquetti.ProjetoLocadora.repository;

import com.riquetti.ProjetoLocadora.entity.LocacaoEntity;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.time.temporal.ChronoUnit;

@Repository
public class CarroDevolucaoRepository {

    private final JdbcTemplate jdbcTemplate;

    public CarroDevolucaoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public LocacaoEntity createDevolucaoIfCarAvailable(LocacaoEntity locacao) {
        // Verificar se o carro foi devolvido
        String checkCarroLocado = "SELECT c.disponivel\n" +
                "FROM locacao l\n" +
                "inner JOIN Carro c ON c.id = l.id_carro\n" +
                "WHERE l.id_locacao = ?";
        Boolean disponivel = jdbcTemplate.queryForObject(checkCarroLocado, Boolean.class, locacao.getIdLocacao());

        if (disponivel == true) {
            throw new IllegalStateException("A devolução já foi realizada anteriormente.");
        }

        // Obter valor de locação do carro
        String carPriceSql = "SELECT c.valorlocacao \n" +
                "FROM locacao l\n" +
                "inner JOIN Carro c ON c.id = l.id_carro\n" +
                "WHERE l.id_locacao = ?";
        BigDecimal valorLocacaoCarro = jdbcTemplate.queryForObject(carPriceSql, BigDecimal.class, locacao.getIdLocacao());

        // Obter valor do seguro da seguradora
        String insurancePriceSql = "SELECT s.valor,\n" +
                "FROM locacao l\n" +
                "inner JOIN seguradora s ON s.id_seguradora = l.id_seguradora\n" +
                "WHERE l.id_locacao = ?;";
        BigDecimal valorSeguro = jdbcTemplate.queryForObject(insurancePriceSql, BigDecimal.class, locacao.getIdLocacao());

        // Calcular o número de dias da locação

        
        long diasLocacao = ChronoUnit.DAYS.between(locacao.getDataLocacao(), locacao.getDataDevolvida());

        // Calcular o valor total
        BigDecimal valorcalculado = valorLocacaoCarro.add(valorSeguro).multiply(BigDecimal.valueOf(diasLocacao));
        System.out.println("Valor calculado: " + valorcalculado);

        BigDecimal valorTotal = valorcalculado.subtract(locacao.getValorDesconto());
        System.out.println("Valor desconto: " + locacao.getValorDesconto());
        System.out.println("Valor total com desconto: " + valorTotal);

        locacao.setValorTotal(valorTotal);


        // Criar devolucao
        String sql = "UPDATE Locacao SET \n" +
                "    dataDevolvida = ?,\n" +
                "    valor = ?,\n" +
                "    valorTotal = ?,\n" +
                "WHERE id_locacao = ?";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id_locacao"});
            ps.setObject(1, locacao.getDataDevolvida());
            ps.setBigDecimal(2, valorcalculado);
            ps.setBigDecimal(3, valorTotal);
            ps.setLong(4, locacao.getIdLocacao());
            return ps;
        }, keyHolder);

        locacao.setIdLocacao(keyHolder.getKey().longValue());

        // Atualizar o carro para disponível
        String updateCarAvailabilitySql = "UPDATE Carro SET disponivel = True WHERE id = ?";
        jdbcTemplate.update(updateCarAvailabilitySql, locacao.getIdCarro());

        return locacao;
    }

}
