package br.com.infuse.desafio.infrastructure.pedido.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    @NotBlank(message = "O código do cliente é obrigatório.")
    @NotNull(message = "O código do cliente é obrigatório.")
    @JsonProperty("codigo_cliente")
    private String codigoCliente;

    @NotBlank(message = "O nome do produto é obrigatório.")
    @JsonProperty("nome")
    private String nome;


}
