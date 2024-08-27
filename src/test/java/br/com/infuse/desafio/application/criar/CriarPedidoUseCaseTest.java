package br.com.infuse.desafio.application.criar;

import br.com.infuse.desafio.domain.exceptions.DomainException;
import br.com.infuse.desafio.domain.pedido.Pedido;
import br.com.infuse.desafio.domain.pedido.PedidoGateway;
import br.com.infuse.desafio.infrastructure.pedido.models.CriarPedidoRequest;
import br.com.infuse.desafio.infrastructure.pedido.models.CriarPedidoResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CriarPedidoUseCaseTest {

    @Mock
    private PedidoGateway pedidoGateway;
    @InjectMocks
    private CriarPedidoUseCase criarPedidoUseCase;

    @Test
    public void dadoPedidosValidos_quandoExecutarUseCase_entaoRetornaPedidosCriados() {
        // setup:
        List<CriarPedidoRequest> command = List.of(
                new CriarPedidoRequest(1234L, LocalDate.now(), BigDecimal.valueOf(100),  2, 1,"Produto A"),
                new CriarPedidoRequest(5678L, LocalDate.now(), BigDecimal.valueOf(200), 1, 2, "Produto B")
        );

        List<Pedido> pedidosCriados = List.of(
                new Pedido(1234L, LocalDate.now(), "Produto A", BigDecimal.valueOf(100), 2, 1),
                new Pedido(5678L, LocalDate.now(), "Produto B", BigDecimal.valueOf(200), 1, 2)
        );

        when(pedidoGateway.criar(anyList())).thenReturn(pedidosCriados);

        // execute:
        List<CriarPedidoResponse> response = criarPedidoUseCase.execute(command);

        // verify:
        assertNotNull(response);
        assertEquals(2, response.size());
        verify(pedidoGateway, times(1)).criar(anyList());
    }

    @Test
    public void dadoNumerosControleDuplicados_quandoExecutarUseCase_entaoLancaExcecao() {
        // setup:
        List<CriarPedidoRequest> command = List.of(
                new CriarPedidoRequest(1234L, LocalDate.now(), BigDecimal.valueOf(100),  2, 1,"Produto A"),
                new CriarPedidoRequest(5678L, LocalDate.now(), BigDecimal.valueOf(200), 1, 2, "Produto B")
        );

        when(pedidoGateway.obterPorNumeroControle(anyList())).thenReturn(
                List.of(new Pedido(1234L, LocalDate.now(), "Produto A", BigDecimal.valueOf(100), 2, 1))
        );

        // execute & verify:
        DomainException exception = assertThrows(DomainException.class, () -> {
            criarPedidoUseCase.execute(command);
        });

        assertTrue(exception.getMessage().contains("números de controle já estão cadastrados"));
    }

    @Test
    public void dadoCodigosClientesInvalidos_quandoExecutarUseCase_entaoLancaExcecao() {
        // setup:
        List<CriarPedidoRequest> command = List.of(
                new CriarPedidoRequest(1234L, LocalDate.now(), BigDecimal.valueOf(100),  2, 11,"Produto A"),
                new CriarPedidoRequest(5678L, LocalDate.now(), BigDecimal.valueOf(200), 1, 12, "Produto B")
        );

        // execute & verify:
        DomainException exception = assertThrows(DomainException.class, () -> {
            criarPedidoUseCase.execute(command);
        });

        assertTrue(exception.getMessage().contains("códigos de cliente são inválidos"));
    }

    @Test
    public void dadoCodigosClientesValidos_quandoExecutarUseCase_entaoNaoLancaExcecao() {
        // setup:
        List<CriarPedidoRequest> command = List.of(
                new CriarPedidoRequest(1234L, LocalDate.now(), BigDecimal.valueOf(100), 2, 1, "Produto A"),
                new CriarPedidoRequest(5678L, LocalDate.now(), BigDecimal.valueOf(200), 1, 2, "Produto B")
        );

        when(pedidoGateway.obterPorNumeroControle(anyList())).thenReturn(Collections.emptyList());
        when(pedidoGateway.criar(anyList())).thenReturn(Collections.emptyList());

        // execute:
        assertDoesNotThrow(() -> criarPedidoUseCase.execute(command));
    }

}
