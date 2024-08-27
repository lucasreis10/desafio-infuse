package br.com.infuse.desafio.domain.pedido;

import org.junit.jupiter.api.Test;


import java.math.BigDecimal;
import java.time.LocalDate;

import static br.com.infuse.desafio.infrastructure.pedido.models.CriarPedidoRequest.builder;
import static java.math.BigDecimal.TEN;
import static java.time.LocalDate.of;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PedidoTest {

    @Test
    public void dadoParametrosValidos_quandoExecutarNewPedido_EntaoPedidoEhInstaciado() {
        // setup:
        final var codigoClienteEsperado = 1;
        final var dataCadastroEsperada = of(2024, 1, 10);
        final var nomeEsperado = "Produto A";
        final var numeroContrleEsperado = 1234L;
        final var quantidadeEsperada = 2;
        final var valorEsperado = TEN;

        final var command = builder()
                .codigoCliente(codigoClienteEsperado)
                .dataCadastro(dataCadastroEsperada)
                .nome(nomeEsperado)
                .numeroControle(numeroContrleEsperado)
                .quantidade(quantidadeEsperada)
                .valor(valorEsperado)
                .build();

        // execute:
        final var pedido = Pedido.newPedido(command);

        // verify:
        assertEquals(codigoClienteEsperado, pedido.getCodigoCliente());
        assertEquals(dataCadastroEsperada, pedido.getDataCadastro());
        assertEquals(nomeEsperado, pedido.getNome());
        assertEquals(numeroContrleEsperado, pedido.getNumeroControle());
        assertEquals(quantidadeEsperada, pedido.getQuantidade());
        assertEquals(valorEsperado, pedido.getValor());
    }

    @Test
    public void dadoQuantidadeNaoFornecida_quandoExecutarNewPedido_EntaoQuantidadeEhUm() {
        // setup:
        final var codigoClienteEsperado = 1;
        final var nomeEsperado = "Produto B";
        final var numeroControleEsperado = 5678L;
        final var valorEsperado = TEN;
        final var valorTotalEsperado = TEN;
        final var quantidadeEsperada = 1;
        final var dataCadastroEsperada = of(2024, 1, 10);

        final var command = builder()
                .codigoCliente(codigoClienteEsperado)
                .nome(nomeEsperado)
                .numeroControle(numeroControleEsperado)
                .dataCadastro(dataCadastroEsperada)
                .valor(valorEsperado)
                .build();

        // execute:
        final var pedido = Pedido.newPedido(command);

        // verify:
        assertEquals(codigoClienteEsperado, pedido.getCodigoCliente());
        assertEquals(dataCadastroEsperada, pedido.getDataCadastro());
        assertEquals(nomeEsperado, pedido.getNome());
        assertEquals(numeroControleEsperado, pedido.getNumeroControle());
        assertEquals(quantidadeEsperada, pedido.getQuantidade());
        assertEquals(valorEsperado, pedido.getValor());
        assertEquals(valorTotalEsperado, pedido.getValorTotal());
    }

    @Test
    public void dadoQuantidadeMaiorOuIgualCinco_quandoExecutarNewPedido_EntaoAplicaDescontoCincoPorcento() {
        // setup:
        final var codigoClienteEsperado = 2;
        final var nomeEsperado = "Produto C";
        final var numeroControleEsperado = 1234L;
        final var quantidadeEsperada = 5;
        final var valorEsperado = BigDecimal.valueOf(100);
        final var valorTotalEsperado = BigDecimal.valueOf(475).setScale(2);

        final var command = builder()
                .codigoCliente(codigoClienteEsperado)
                .nome(nomeEsperado)
                .numeroControle(numeroControleEsperado)
                .quantidade(quantidadeEsperada)
                .valor(valorEsperado)
                .build();

        // execute:
        final var pedido = Pedido.newPedido(command);

        // verify:
        assertEquals(quantidadeEsperada, pedido.getQuantidade());
        assertEquals(valorEsperado, pedido.getValor());
        assertEquals(valorTotalEsperado, pedido.getValorTotal());
    }

    @Test
    public void dadoQuantidadeMaiorOuIgualDez_quandoExecutarNewPedido_EntaoAplicaDescontoDezPorcento() {
        // setup:
        final var codigoClienteEsperado = 3;
        final var nomeEsperado = "Produto D";
        final var numeroControleEsperado = 7890L;
        final var quantidadeEsperada = 10;
        final var valorEsperado = BigDecimal.valueOf(50);
        final var valorTotalEsperado = BigDecimal.valueOf(450).setScale(2);

        final var command = builder()
                .codigoCliente(codigoClienteEsperado)
                .nome(nomeEsperado)
                .numeroControle(numeroControleEsperado)
                .quantidade(quantidadeEsperada)
                .valor(valorEsperado)
                .build();

        // execute:
        final var pedido = Pedido.newPedido(command);

        // verify:
        assertEquals(quantidadeEsperada, pedido.getQuantidade());
        assertEquals(valorEsperado, pedido.getValor());
        assertEquals(valorTotalEsperado, pedido.getValorTotal());
    }

    @Test
    public void dadoDataCadastroNaoFornecida_quandoExecutarNewPedido_EntaoDataCadastroEhDataAtual() {
        // setup:
        final var codigoClienteEsperado = 4;
        final var nomeEsperado = "Produto E";
        final var numeroControleEsperado = 1122L;
        final var quantidadeEsperada = 3;
        final var valorEsperado = BigDecimal.valueOf(30);

        final var command = builder()
                .codigoCliente(codigoClienteEsperado)
                .nome(nomeEsperado)
                .numeroControle(numeroControleEsperado)
                .quantidade(quantidadeEsperada)
                .valor(valorEsperado)
                .build();

        // execute:
        final var pedido = Pedido.newPedido(command);

        // verify:
        assertEquals(LocalDate.now(), pedido.getDataCadastro());
    }

    @Test
    public void dadoQuantidadeMenorQueCinco_quandoExecutarNewPedido_EntaoValorTotalSemDesconto() {
        // setup:
        final var codigoClienteEsperado = 5;
        final var nomeEsperado = "Produto F";
        final var numeroControleEsperado = 3344L;
        final var quantidadeEsperada = 3;
        final var valorEsperado = BigDecimal.valueOf(40);

        final var command = builder()
                .codigoCliente(codigoClienteEsperado)
                .nome(nomeEsperado)
                .numeroControle(numeroControleEsperado)
                .quantidade(quantidadeEsperada)
                .valor(valorEsperado)
                .build();

        // execute:
        final var pedido = Pedido.newPedido(command);

        // verify:
        assertEquals(quantidadeEsperada, pedido.getQuantidade());
        assertEquals(valorEsperado, pedido.getValor());
        assertEquals(BigDecimal.valueOf(120), pedido.getValorTotal());
    }

}
