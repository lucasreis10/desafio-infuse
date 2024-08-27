package br.com.infuse.desafio.infrastructure.pedido.persistence;

import br.com.infuse.desafio.domain.pedido.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByNumeroControleIn(List<Long> numerosControle);

    @Query("SELECT p FROM Pedido p WHERE " +
            "(:numeroControle IS NULL OR p.numeroControle = :numeroControle) AND " +
            "(:dataCadastro IS NULL OR p.dataCadastro = :dataCadastro) AND " +
            "(:nome IS NULL OR :nome = '' OR p.nome = :nome) AND " +
            "(:codigoCliente IS NULL OR p.codigoCliente = :codigoCliente)")
    List<Pedido> findBy(
            @Param("numeroControle") Long numeroControle,
            @Param("dataCadastro") LocalDate dataCadastro,
            @Param("nome") String nome,
            @Param("codigoCliente") Long codigoCliente
    );


}
