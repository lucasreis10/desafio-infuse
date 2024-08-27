package br.com.infuse.desafio.infrastructure.pedido.models;

import br.com.infuse.desafio.domain.pedido.Pedido;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CriarPedidoResponse {

    @JsonProperty("numero_controle")
    private Long numeroControle;

    public static CriarPedidoResponse from(Pedido pedido) {
        return new CriarPedidoResponse(pedido.getNumeroControle());
    }
}
