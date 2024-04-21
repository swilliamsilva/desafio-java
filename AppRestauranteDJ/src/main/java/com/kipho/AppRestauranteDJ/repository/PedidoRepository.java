package com.kipho.AppRestauranteDJ.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.kipho.AppRestauranteDJ.models.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}

