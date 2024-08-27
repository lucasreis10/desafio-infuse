package br.com.infuse.desafio.infrastructure.pedido;

import br.com.infuse.desafio.domain.pedido.Pedido;
import br.com.infuse.desafio.domain.pedido.PedidoGateway;
import br.com.infuse.desafio.infrastructure.pedido.persistence.PedidoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class PedidoMySQLGateway implements PedidoGateway {

    private final PedidoRepository repository;

    @Override
    public List<Pedido> criar(List<Pedido> pedidos) {
        return repository.saveAll(pedidos);
    }

    @Override
    public List<Pedido> obterPorNumeroControle(List<Long> numerosControle) {
        return repository.findByNumeroControleIn(numerosControle)
                .stream()
                .toList();
    }
}
