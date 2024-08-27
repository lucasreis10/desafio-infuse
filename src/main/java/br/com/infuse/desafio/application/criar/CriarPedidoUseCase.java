package br.com.infuse.desafio.application.criar;

import br.com.infuse.desafio.domain.exceptions.DomainException;
import br.com.infuse.desafio.domain.pedido.Pedido;
import br.com.infuse.desafio.domain.pedido.PedidoGateway;
import br.com.infuse.desafio.infrastructure.pedido.models.CriarPedidoRequest;
import br.com.infuse.desafio.infrastructure.pedido.models.CriarPedidoResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class CriarPedidoUseCase {

    private final Set<Integer> CODIGOS_CLIENTES_VALIDOS = Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);


    private PedidoGateway pedidoGateway;

    public List<CriarPedidoResponse> execute(final List<CriarPedidoRequest> command) {
        validarPayloadRequest(command);

        final var pedidos = command.stream().map(Pedido::newPedido).toList();

        final var pedidosCriados = pedidoGateway.criar(pedidos);

        return pedidosCriados.stream().map(CriarPedidoResponse::from).toList();

    }

    private void validarPayloadRequest(List<CriarPedidoRequest> command) {
        validarNumerosControle(command);
        validarCodigoCliente(command);
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


    private void validarCodigoCliente(final List<CriarPedidoRequest> command) {
       final var codigosClientesInvalidos = obterClientesInvalidos(command);

        if (!codigosClientesInvalidos.isEmpty()) {
            throw new DomainException("Os seguintes códigos de cliente são inválidos: " + codigosClientesInvalidos);
        }
    }

    private List<Integer> obterClientesInvalidos(List<CriarPedidoRequest> command) {
        return command.stream()
                .map(CriarPedidoRequest::getCodigoCliente)
                .filter(codigoCliente -> !CODIGOS_CLIENTES_VALIDOS.contains(codigoCliente))
                .toList();
    }

    private List<Long> obterNumerosControle(final List<CriarPedidoRequest> command) {
        return command.stream().map(CriarPedidoRequest::getNumeroControle).toList();
    }

}
