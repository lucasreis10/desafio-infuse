package br.com.infuse.desafio.application;

import br.com.infuse.desafio.domain.exceptions.DomainException;
import br.com.infuse.desafio.domain.pedido.Pedido;
import br.com.infuse.desafio.domain.pedido.PedidoGateway;
import br.com.infuse.desafio.infrastructure.pedido.models.CriarPedidoRequest;
import br.com.infuse.desafio.infrastructure.pedido.models.CriarPedidoResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CriarPedidoUseCase {

    private PedidoGateway pedidoGateway;

    public List<CriarPedidoResponse> execute(final List<CriarPedidoRequest> command) {
        validarNumerosControle(command);

        final var pedidos = command.stream().map(Pedido::newPedido).toList();

        final var pedidosCriados = pedidoGateway.criar(pedidos);

        return pedidosCriados.stream().map(CriarPedidoResponse::from).toList();

    }

    private void validarNumerosControle(final List<CriarPedidoRequest> command) {
        final var  numerosControle = obterNumerosControle(command);

        final var numerosControleExistentes = pedidoGateway.obterPorNumeroControle(numerosControle)
                .stream()
                .map(Pedido::getNumeroControle)
                .toList();

        if (!numerosControleExistentes.isEmpty()) {
            throw new DomainException("ERROR, Os seguintes números de controle já estão cadastrados: " + numerosControleExistentes);
        }

    }

    private List<Long> obterNumerosControle(final List<CriarPedidoRequest> command) {
        return command.stream().map(CriarPedidoRequest::getNumeroControle).toList();
    }

}
