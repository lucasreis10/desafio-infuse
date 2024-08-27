package br.com.infuse.desafio.domain.pedido;

import java.time.LocalDate;
import java.util.List;

public interface PedidoGateway {

    List<Pedido> criar(final List<Pedido> pedidos);

    List<Pedido> obterPorNumeroControle(final List<Long> numerosControle);

    List<Pedido> obterPedidosPor(final Long numeroControle, final LocalDate dataCadastro, final String nome, final Long codigoCliente);

}
