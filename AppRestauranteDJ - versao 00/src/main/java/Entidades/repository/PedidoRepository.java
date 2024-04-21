package Entidades.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Entidades.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
