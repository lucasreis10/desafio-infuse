package br.com.infuse.desafio.application.recuperar;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class SearchQuery {

    private Long numeroControle;
    private LocalDate dataCadastro;
    private String nome;
    private Long codigoCliente;

}
