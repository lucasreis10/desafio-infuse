package br.com.infuse.desafio.infrastructure.api.controllers;

import br.com.infuse.desafio.application.CriarPedidoUseCase;
import br.com.infuse.desafio.domain.exceptions.DomainException;
import br.com.infuse.desafio.infrastructure.pedido.models.CriarPedidoRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "pedidos")
@Tag(name = "Pedidos")
@AllArgsConstructor
public class PedidosController {

    private CriarPedidoUseCase criarPedidoUseCase;

    @PostMapping(
        consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Criar um novo pedido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode =  "201", description = "Criado com sucesso."),
            @ApiResponse(responseCode =  "422", description = "Um erro de validação foi lançado."),
            @ApiResponse(responseCode =  "500", description = "Um erro interno foi lançado.")
    })
    ResponseEntity<?> criarCategoria(@RequestBody @Valid List<CriarPedidoRequest> input){
        if(input.size() > 10) {
            throw new DomainException("Não é permitido uma lista com mais de 10 pedidos.");
        }

        return ResponseEntity.ok(criarPedidoUseCase.execute(input));
    }


}
