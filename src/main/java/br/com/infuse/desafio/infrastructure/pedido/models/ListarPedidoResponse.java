package br.com.infuse.desafio.infrastructure.pedido.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
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
    private String codigoCliente;
    @JsonProperty("nome")
    private String nome;

}
