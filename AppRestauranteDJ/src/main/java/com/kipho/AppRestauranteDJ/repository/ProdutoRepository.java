package com.kipho.AppRestauranteDJ.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kipho.AppRestauranteDJ.models.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}

