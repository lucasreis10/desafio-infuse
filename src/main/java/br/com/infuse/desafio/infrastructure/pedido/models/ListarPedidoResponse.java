package br.com.infuse.desafio.infrastructure.pedido.models;

import br.com.infuse.desafio.domain.pedido.Pedido;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ListarPedidoResponse {

    @JsonProperty("numero_controle")
    private Long numeroControle;
    @JsonProperty("data_cadastro")
    private LocalDate dataCadastro;
    @JsonProperty("valor")
    private BigDecimal valor;
    @JsonProperty("quantidade")
    private Integer quantidade;
    @JsonProperty("codigo_cliente")
    private Long codigoCliente;
    @JsonProperty("nome")
    private String nome;

    public static ListarPedidoResponse from(final Pedido pedido) {
        return new ListarPedidoResponse(
            pedido.getNumeroControle(),
            pedido.getDataCadastro(),
            pedido.getValor(),
            pedido.getQuantidade(),
            pedido.getCodigoCliente(),
            pedido.getNome()
        );
    }
}
