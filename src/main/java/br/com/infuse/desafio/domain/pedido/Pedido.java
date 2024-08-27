package br.com.infuse.desafio.domain.pedido;


import br.com.infuse.desafio.infrastructure.pedido.models.CriarPedidoRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@Entity
@Table(name = "pedido")
@Getter
@NoArgsConstructor
public class Pedido {

    @Id
    private Long numeroControle;
    private LocalDate dataCadastro;
    private String nome;
    private BigDecimal valor;
    private Integer quantidade;
    private Long codigoCliente;
    private BigDecimal valorTotal;


    public Pedido(
        final Long numeroControle,
        final LocalDate dataCadastro,
        final String nome,
        final BigDecimal valor,
        final Integer quantidade,
        final Long codigoCliente
    ) {
        this.numeroControle = numeroControle;
        this.dataCadastro = ofNullable(dataCadastro).orElse(LocalDate.now());
        this.nome = nome;
        this.valor = valor;
        this.quantidade = ofNullable(quantidade).orElse(1);
        this.codigoCliente = codigoCliente;
        this.valorTotal = calcularValorTotal();
    }

    public static Pedido newPedido(CriarPedidoRequest command) {
        return new Pedido(
                command.getNumeroControle(),
                ofNullable(command.getDataCadastro()).orElse(LocalDate.now()),
                command.getNome(),
                command.getValor(),
                command.getQuantidade(),
                command.getNumeroControle()
        );
    }

    private BigDecimal calcularValorTotal() {
        BigDecimal total = valor.multiply(BigDecimal.valueOf(quantidade));

        if (quantidade >= 10) {
            total = total.multiply(BigDecimal.valueOf(0.90));
        } else if (quantidade >= 5) {
            total = total.multiply(BigDecimal.valueOf(0.95));
        }

        return total;
    }

}
