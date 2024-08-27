package br.com.infuse.desafio.domain.pedido;

import java.util.List;

public interface PedidoGateway {

    List<Pedido> criar(final List<Pedido> pedidos);

    List<Pedido> obterPorNumeroControle(final List<Long> numerosControle);

}
