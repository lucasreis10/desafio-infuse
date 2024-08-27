package br.com.infuse.desafio.infrastructure.pedido;

import br.com.infuse.desafio.domain.pedido.Pedido;
import br.com.infuse.desafio.domain.pedido.PedidoGateway;
import br.com.infuse.desafio.infrastructure.pedido.persistence.PedidoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@AllArgsConstructor
public class PedidoMySQLGateway implements PedidoGateway {

    private final PedidoRepository repository;

    @Override
    public List<Pedido> criar(final List<Pedido> pedidos) {
        return repository.saveAll(pedidos);
    }

    @Override
    public List<Pedido> obterPorNumeroControle(final List<Long> numerosControle) {
        return repository.findByNumeroControleIn(numerosControle)
                .stream()
                .toList();
    }

    @Override
    public List<Pedido> obterPedidosPor(
            final Long numeroControle,
            final LocalDate dataCadastro,
            final String nome,
            final Long codigoCliente
    ) {
        return repository.findBy(
            numeroControle,
            dataCadastro,
            nome,
            codigoCliente
        );
    }
}
