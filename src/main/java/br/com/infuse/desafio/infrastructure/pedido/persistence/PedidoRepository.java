package br.com.infuse.desafio.infrastructure.pedido.persistence;

import br.com.infuse.desafio.domain.pedido.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByNumeroControleIn(List<Long> numerosControle);


}
