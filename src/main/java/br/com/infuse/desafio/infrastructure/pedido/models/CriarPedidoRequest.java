package br.com.infuse.desafio.infrastructure.pedido.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CriarPedidoRequest {

    @NotNull
    @JsonProperty("numero_controle")
    private Long numeroControle;

    @JsonProperty("data_cadastro")
    private LocalDate dataCadastro;

    @NotNull(message = "O valor do produto é obrigatório.")
    @JsonProperty("valor")
    private BigDecimal valor;

    @JsonProperty("quantidade")
    private Integer quantidade;

    @NotNull(message = "O código do cliente é obrigatório.")
    @JsonProperty("codigo_cliente")
    private Integer codigoCliente;

    @NotBlank(message = "O nome do produto é obrigatório.")
    @JsonProperty("nome")
    private String nome;


}
