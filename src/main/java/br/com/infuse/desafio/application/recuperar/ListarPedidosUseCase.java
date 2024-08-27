package br.com.infuse.desafio.application.recuperar;

import br.com.infuse.desafio.domain.pedido.PedidoGateway;
import br.com.infuse.desafio.infrastructure.pedido.models.ListarPedidoResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ListarPedidosUseCase {

    private PedidoGateway pedidoGateway;

    public List<ListarPedidoResponse> execute(SearchQuery command) {
        final var pedidos = pedidoGateway.obterPedidosPor(
                command.getNumeroControle(),
                command.getDataCadastro(),
                command.getNome(),
                command.getCodigoCliente()
        );

        return pedidos.stream()
                .map(ListarPedidoResponse::from)
                .toList();

    }

}
