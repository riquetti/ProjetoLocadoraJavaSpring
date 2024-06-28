package com.riquetti.ProjetoLocadora.repository;

import com.riquetti.ProjetoLocadora.entity.LocacaoEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.time.temporal.ChronoUnit;

@Repository
public class CarroLocacaoRepository {

    private final JdbcTemplate jdbcTemplate;

    public CarroLocacaoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public LocacaoEntity createLocacao(LocacaoEntity locacao) {
        // Verificar se o carro está disponível
        String checkCarAvailability = "SELECT disponivel FROM Carro WHERE id = ?";
        Boolean disponivel = jdbcTemplate.queryForObject(checkCarAvailability, Boolean.class, locacao.getIdCarro());

        if (disponivel == null || !disponivel) {
            throw new IllegalStateException("Carro não disponível para locação.");
        }

        // Obter valor de locação do carro
        String carPriceSql = "SELECT valorLocacao FROM Carro WHERE id = ?";
        BigDecimal valorLocacaoCarro = jdbcTemplate.queryForObject(carPriceSql, BigDecimal.class, locacao.getIdCarro());

        // Obter valor do seguro da seguradora
        String insurancePriceSql = "SELECT valor FROM Seguradora WHERE id_seguradora = ?";
        BigDecimal valorSeguro = jdbcTemplate.queryForObject(insurancePriceSql, BigDecimal.class, locacao.getIdSeguradora());

        // Calcular o número de dias da locação
        long diasLocacao = ChronoUnit.DAYS.between(locacao.getDataLocacao(), locacao.getDataDevolucao());

        // Calcular o valor total
        BigDecimal valorcalculado = valorLocacaoCarro.add(valorSeguro).multiply(BigDecimal.valueOf(diasLocacao));
        System.out.println("Valor calculado: " + valorcalculado);

        BigDecimal valorTotal = valorcalculado.subtract(locacao.getValorDesconto());
        System.out.println("Valor desconto: " + locacao.getValorDesconto());
        System.out.println("Valor total com desconto: " + valorTotal);

        locacao.setValorTotal(valorTotal);


        // Criar locação
        String sql = "INSERT INTO Locacao (\n" +
                "    dataLocacao, dataDevolucao, valor, valorDesconto, valorTotal, id_carro, id_seguradora, id_cliente\n" +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id_locacao"});
            ps.setObject(1, locacao.getDataLocacao());
            ps.setObject(2, locacao.getDataDevolucao());
            ps.setBigDecimal(3, valorcalculado);
            ps.setBigDecimal(4, locacao.getValorDesconto());
            ps.setBigDecimal(5, valorTotal);
            ps.setLong(6, locacao.getIdCarro());
            ps.setLong(7, locacao.getIdSeguradora());
            ps.setLong(8, locacao.getIdCliente());
            return ps;
        }, keyHolder);

        locacao.setIdLocacao(keyHolder.getKey().longValue());

        // Atualizar o carro para indisponível
        String updateCarAvailabilitySql = "UPDATE Carro SET disponivel = false WHERE id = ?";
        jdbcTemplate.update(updateCarAvailabilitySql, locacao.getIdCarro());

        return locacao;
    }

}
